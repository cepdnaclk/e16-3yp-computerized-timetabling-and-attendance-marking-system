package Group10.example.API.DAO;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.AttendanceRepository;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AttendanceDAO {
    private final AttendanceRepository attendanceRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceDAO(AttendanceRepository attendanceRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public String registerCourses(CourseRegModel courseRegModel) {
        Optional<Student> student = studentRepository.findById(courseRegModel.getStudentId());
        if(!student.isPresent())return "not success";
        //all courses that registered in previous semester must be removed
        student.ifPresent(s -> {
            s.removeAllCourses();
            attendanceRepository.removeAllByStudentId(s.getStudentID());
            studentRepository.save(s);
        });

        for (String courseId: courseRegModel.getCourseList()) {
            Optional<Course> course = courseRepository.findById(courseId);
            course.ifPresent(c -> {
                c.addStudent(student);
                student.ifPresent(s -> {
                    s.addCourse(c);
                    Attendance attendance = new Attendance();
                    attendance.setStudentId(s.getStudentID());
                    attendance.setCourseId(c.getCourseId());
                    attendance.initialize();
                    Attendance a = attendanceRepository.insert(attendance);
                    System.out.println(a.getAttendanceId());
                    studentRepository.save(s);
                    courseRepository.save(c);
                });
            });
        }
        return "success";
    }

    public Collection<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Collection<Course> findCoursesByStudentId(String studentId) {
        Collection<Attendance> attendances = attendanceRepository.findByStudentId(studentId);
        HashSet<Course> courseList = new HashSet<>();
        for (Attendance a: attendances) {
            Optional<Course> course = courseRepository.findById(a.getCourseId());
            course.ifPresent(courseList::add);
        }
        return courseList;
    }

    public Collection<Student> findStudentsByCourseId(String courseId) {
        Collection<Attendance> attendances = attendanceRepository.findByCourseId(courseId);
        HashSet<Student> studentList = new HashSet<>();
        for (Attendance a: attendances) {
            Optional<Student> student = studentRepository.findById(a.getStudentId());
            student.ifPresent(studentList::add);
        }
        return studentList;
    }

    public Collection<AttendanceTemplate> findAttendancesByCourseId(String courseId) {
        Collection<Attendance> attendances = attendanceRepository.findByCourseId(courseId);
        HashSet<AttendanceTemplate> attendanceTemplates = new HashSet<>();
        for (Attendance a: attendances) {
            Optional<Student> student = studentRepository.findById(a.getStudentId());
            student.ifPresent(s -> attendanceTemplates.add(new AttendanceTemplate(a,s.getFirstName()+" "+s.getLastName(),s.getRegNumber())));
        }
        return attendanceTemplates;
    }

    public Attendance findAttendanceByStudentAndCourse(String courseId, String studentId) {
        return attendanceRepository.findByCourseIdAndStudentId(courseId,studentId).iterator().next();
    }

    public String addAttendanceLog(String courseId, String studentId, AttendanceItem attendanceItem) {
        Collection<Attendance> attendances = attendanceRepository.findByCourseIdAndStudentId(courseId,studentId);
        if(attendances.size() != 1){
            return "not success";
        }
        //this must have only one item
        Attendance attendance = attendances.iterator().next();//get first item
        attendance.addAttendanceItem(attendanceItem);
        attendanceRepository.save(attendance);
        return "success";
    }

    public String addCompleteAttendanceRecord(AttendanceLogList list) {
        ArrayList<String> allStudents = new ArrayList<>(findStudentIdListByCourseId(list.getCourseId()));
        HashSet<String> presentStudents = new HashSet<>(list.getStudentIdList());
        AttendanceItem presentAttendanceItem = new AttendanceItem(list.getDate(),list.getTime(),list.getLab_or_lecture(),true);
        AttendanceItem absentAttendanceItem = new AttendanceItem(list.getDate(),list.getTime(),list.getLab_or_lecture(),false);
        for (String s: allStudents) {
            Attendance a = attendanceRepository.findByCourseIdAndStudentId(list.getCourseId(),s).iterator().next();
            if(presentStudents.contains(s)){
                a.addAttendanceItem(presentAttendanceItem);
            }
            else{
                a.addAttendanceItem(absentAttendanceItem);
            }
            attendanceRepository.save(a);
        }
        return "success";
    }

    public ArrayList<String> findCourseIdListByStudentId(String studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        ArrayList<String> courseIdList = new ArrayList<>();
        student.ifPresent(s -> courseIdList.addAll(s.getCourseSet()));
        return courseIdList;
    }

    public ArrayList<String> findStudentIdListByCourseId(String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        ArrayList<String> studentIdList = new ArrayList<>();
        course.ifPresent(c -> studentIdList.addAll(c.getStudentsIds()));
        return studentIdList;
    }

    public void deleteByStudentId(String studentId) {
        attendanceRepository.removeAllByStudentId(studentId);
    }

    public void deleteByCourseId(String courseId) {
        attendanceRepository.removeAllByCourseId(courseId);
    }



}
