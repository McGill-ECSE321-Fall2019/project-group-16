package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.TutoringSystemApplication;
import ca.mcgill.ecse321.tutoringsystem.dto.CourseDto;
import ca.mcgill.ecse321.tutoringsystem.dto.StudentDto;
import ca.mcgill.ecse321.tutoringsystem.dto.UniversityDto;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.University;
import ca.mcgill.ecse321.tutoringsystem.service.TutoringSystemService;


@RestController
@CrossOrigin(origins = "*")
public class TutoringSystemRestController {
   
	@Autowired
	TutoringSystemService service;
	
	// <-------------- Register and LogIn ------------>

	//list of students registered
	@GetMapping(value = {"/students" , "/students/"})
	public List<StudentDto> getAllStudents(){
		List<StudentDto> studentDtos = new ArrayList<>();
		for(Student s : service.getAllStudents()) {
			studentDtos.add(convertToDto(s));
		}
		return studentDtos;
	}
	
	// <----------------- Searching for Courses ---------------->
	
	//get all courses
	@GetMapping(value = {"/courses", "/courses/"})
	public List<CourseDto> getAllCourses(){
		List<CourseDto> courseDtos = new ArrayList<>();
		for(Course c: service.getAllCourses()) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
		
	}
	//search by courseCode
	@GetMapping(value= {"/courses/{code}", "/courses/{code}/"})
	public CourseDto getCourseByCourseCode(@PathVariable("code") String code){
		Course c = service.getCourse(code);
		if(c==null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		return convertToDto(c);
	}
	
	//search courses using university
	@GetMapping(value= {"/courses/{university}" , "/courses/{university}"})
	public List<CourseDto> getCoursesForUniversity(@PathVariable("university") String university){
		University u = service.getUniversity(university);
		List<CourseDto> courseDtos = new ArrayList<>();
		if(u == null) {
			throw new IllegalArgumentException("There is no such university!");
		}
		Set<Course> courses = u.getCourse();
		for(Course c : courses) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
	}
	
	// <-------------- Searching for University --------------->
	
	// Get all the schools offered by the application
	@GetMapping(value = { "/universities", "/universities/" })
	public List<UniversityDto> getAllUniversities() {
		List<UniversityDto> universityDtos = new ArrayList<>();
			// get universities from the tutoring service
		for (University university : service.getAllUniversities()) {
				// convert model class to a data transfer object
			universityDtos.add(convertToDto(university));
		}
		return universityDtos;
	}
	
		// <--------------------- Post Mappings ---------------------->
	
	//register new student
	@PostMapping(value = {"/student/{username}/{password}/{name}/{schoolName}", "/student/{username}/{password}/{name}/{schoolName}/"})
	public StudentDto registerStudent(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("name") String name, @PathVariable("schoolName") String schoolName){
		Student s = service.createStudent(username, password, name, schoolName);
		return convertToDto(s);
	}
	
	// log in student
	@PostMapping(value = {"/student/{username}/{password}", "/student/{username}/{password}/"})
	public void loginStudent(@PathVariable("username") String username, @PathVariable("password") String password) {
		Student s = service.getStudent(username);
		if(s==null) throw new IllegalArgumentException("There is no such student!");
		String studentPass = s.getPassword();
		if(password == studentPass) {
			// needs to be completed
			//TutoringSystemApplication.setCurrentStudent(s);
		}
	}
	
	//enter university to system
	@PostMapping(value = {"/university/{id}/{name}", "/university/{id}/{name}/"})
	public UniversityDto enterUniversity(@PathVariable("id") int id, @PathVariable("name") String name){
		University u = service.createUniversity(id, name);
		UniversityDto UDto = convertToDto(u);
		return UDto;	
	}
	
	// <--------------------- DTOs ------------------>
	
	private StudentDto convertToDto(Student s) {
		if(s==null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		StudentDto sDto = new StudentDto(s.getUsername(), s.getPassword(), s.getName(),s.getSchoolName());
		return sDto;
	}
	
	private UniversityDto convertToDto(University u) {
		if (u == null) {
			throw new IllegalArgumentException("There is no such University!");
		}
		UniversityDto uDTO = new UniversityDto(u.getId(), u.getName());
		return uDTO;
	}
	
	private CourseDto convertToDto(Course c) {
		if(c == null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		CourseDto cDto = new CourseDto(c.getCourseCode(),c.getSubject(),c.getUniversity());
		return cDto;
	}
}