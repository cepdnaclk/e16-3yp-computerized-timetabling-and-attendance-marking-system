package Group10.example.API.Service;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Group10.example.API.Model.Group;
import Group10.example.API.Repository.GroupRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class LecturerService {
	
	@Autowired 
	GroupRepository groupRepository;
	
	@Autowired
	GroupService groupService;

	@Autowired
	LecturerRepository lecturerRepository;

	@Autowired
	CourseRepository courseRepository;
	
	public Group createStudentGroup(Group group) {
		
		return groupRepository.insert(group);
		
	}
	
	public void deleteStudentGroup(String groupId) {
		
		groupRepository.deleteById(groupId);
		
	}


	public Optional<Lecturer> addCourseToLecturer(String lecturerId, String courseId) {
		System.out.println("addCourseToLecturer Service");
		Optional<Lecturer> lecturer = lecturerRepository.findById(lecturerId);
		Optional<Course> course = courseRepository.findById(courseId);
		System.out.println("course = "+course.isPresent());
		System.out.println("lecturer = "+lecturer.isPresent());
		if(course.isPresent()){
			lecturer.ifPresent(l -> {
				System.out.println("addCourse");
				l.addCourse(course);
				lecturerRepository.save(l);
			});
		}
		if(lecturer.isPresent()){
			course.ifPresent(c -> {
				System.out.println("addLecturer");
				c.addLecturer(lecturer);
				courseRepository.save(c);
			});
		}
		return lecturer;
	}


	public Optional<Lecturer> removeCourseToLecturer(String lecturerId, String courseId) {
		Optional<Lecturer> lecturer = lecturerRepository.findById(lecturerId);
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent()){
			lecturer.ifPresent(l -> {
				l.removeCourse(course);
				lecturerRepository.save(l);
			});
		}
		if(lecturer.isPresent()){
			course.ifPresent(c -> {
				c.removeLecturer(lecturer);
				courseRepository.save(c);
			});
		}
		return lecturer;
	}

	public Collection<Lecturer> findAll() {
		return lecturerRepository.findAll();
	}

	public Optional<Lecturer> findById(String id) {
		return lecturerRepository.findById(id);
	}
}
