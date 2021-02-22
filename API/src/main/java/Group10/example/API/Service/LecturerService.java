package Group10.example.API.Service;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Group10.example.API.Model.Group;
import Group10.example.API.Repository.GroupRepository;

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
		Optional<Lecturer> lecturer = lecturerRepository.findById(lecturerId);
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent()){
			lecturer.ifPresent(l -> l.addCourse(course));
		}
		if(lecturer.isPresent()){
			course.ifPresent(c -> c.addLecturer(lecturer));
		}
		return lecturer;
	}


	public Optional<Lecturer> removeCourseToLecturer(String lecturerId, String courseId) {
		Optional<Lecturer> lecturer = lecturerRepository.findById(lecturerId);
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent()){
			lecturer.ifPresent(l -> l.removeCourse(course));
		}
		if(lecturer.isPresent()){
			course.ifPresent(c -> c.removeLecturer(lecturer));
		}
		return lecturer;
	}
}
