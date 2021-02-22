package Group10.example.API.Controller;

import Group10.example.API.Model.*;
import Group10.example.API.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //Course Registration
    @PostMapping(value = "courses/registration/add")//checked
    public String registerCourses(@Valid @RequestBody CourseRegModel courseRegModel){
        return attendanceService.registerCourses(courseRegModel);
    }

    @GetMapping(value = "attendance/all")//checked
    public Collection<Attendance> getAll(){
        return attendanceService.findAll();
    }

    //2 methods
    //first one returns all Course objects
    //second one returns all CourseIds -> fast
    @GetMapping(value = "courses/findcoursesbystudentid/{id}")//checked
    public Collection<Course> findCoursesByStudentId(@PathVariable("id")String studentId){
        return attendanceService.findCoursesByStudentId(studentId);
    }

    @GetMapping(value = "courses/findcourseidlistbystudentid/{id}")//checked
    public ArrayList<String> findCourseIdListByStudentId(@PathVariable("id")String studentId){
        return attendanceService.findCourseIdListByStudentId(studentId);
    }

    //2 methods
    //first one returns all Student objects
    //second one returns all StudentIds -> fast
    @GetMapping(value = "courses/findstudentsbycourseid/{id}")//checked
    public Collection<Student> findStudentsByCourseId(@PathVariable("id")String courseId){
        return attendanceService.findStudentsByCourseId(courseId);
    }

    @GetMapping(value = "courses/findstudentidlistcourseid/{id}")//checked
    public Collection<String> findStudentIdListByCourseId(@PathVariable("id")String courseId){
        return attendanceService.findStudentIdListByCourseId(courseId);
    }

    //this must return only one object (collection has only one object)
    @GetMapping(value = "attendance/findattendancebystudentidandcourseid")//checked
    public Attendance findAttendanceByStudentAndCourse(@RequestParam(name = "course")String courseId,
                                                                   @RequestParam(name = "student")String studentId){
        return attendanceService.findAttendanceByStudentAndCourse(courseId,studentId);
    }

    //adding attendance log (done by device itself)
    //adding single log item
    @PostMapping(value = "attendance/addlog")//checked
    public String addAttendanceLog(@RequestParam(name = "course")String courseId,
                                   @RequestParam(name = "student")String studentId,
                                   @Valid @RequestBody AttendanceItem attendanceItem){
        return attendanceService.addAttendanceLog(courseId,studentId,attendanceItem);
    }
    //adding complete attendance records of the class
    @PostMapping(value = "attendance/addcompleterecord")//checked
    public String addCompleteAttendanceRecord(@Valid @RequestBody AttendanceLogList attendanceLogList){
        return attendanceService.addCompleteAttendanceRecord(attendanceLogList);
    }


    @DeleteMapping(value = "attendance/deletebystudentid/{id}")
    public void deleteAllByStudentId(@PathVariable("id")String studentId){
        attendanceService.deleteByStudentId(studentId);
    }

    @DeleteMapping(value = "attendance/deletebycourseid/{id}")
    public void deleteAllByCourseId(@PathVariable("id")String courseId){
        attendanceService.deleteByCourseId(courseId);
    }

}
