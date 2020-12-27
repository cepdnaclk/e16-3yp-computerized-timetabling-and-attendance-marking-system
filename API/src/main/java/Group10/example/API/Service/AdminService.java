package Group10.example.API.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;

import Group10.example.API.Model.Student;
import Group10.example.API.Model.Lecturer;

public class AdminService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	LecturerRepository lecturerRepository;
	
	public Student insertStudent(Student student) {
		
		return studentRepository.insert(student);
		
	}
	
	public void deleteStudent(String id) {
		
		studentRepository.deleteById(id);
		
	}
	
	public Student modifyStudent(Student student) {
		
		Optional<Student> tmpStudent = studentRepository.findById(student.getStudentID());
		
		if(tmpStudent != null) return studentRepository.save(student);
		
		return null;
		
	}
	
	public List<Student> getAllStudents(){
		
		return studentRepository.findAll();
	}
	
	public Lecturer insertLecturer(Lecturer lecturer) {
		
		return lecturerRepository.insert(lecturer);
		
	}
	
	public void deleteLecturer(String id) {
		
		lecturerRepository.deleteById(id);
		
	}
	
	public Lecturer modifyLecturer(Lecturer lecturer) {
		
		Optional<Lecturer> tmpStudent = lecturerRepository.findById(lecturer.getLectID());
		
		if(tmpStudent != null) return lecturerRepository.save(lecturer);
		
		return null;
		
	}
	
	public List<Lecturer> getAllLecturers(){
		
		return lecturerRepository.findAll();
	}


}
