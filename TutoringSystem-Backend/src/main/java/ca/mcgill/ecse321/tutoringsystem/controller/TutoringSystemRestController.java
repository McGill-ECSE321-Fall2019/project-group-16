package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.StudentDto;
import ca.mcgill.ecse321.tutoringsystem.dto.UniversityDto;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.University;
import ca.mcgill.ecse321.tutoringsystem.service.TutoringSystemService;


@RestController
@CrossOrigin(origins = "*")
public class TutoringSystemRestController {
   
	@Autowired
	TutoringSystemService service;
	
	@GetMapping(value = {"/students" , "/students/"})
	public List<StudentDto> getAllStudents(){
		List<StudentDto> studentDtos = new ArrayList<>();
		
		for(Student s : service.getAllStudents()) {
			studentDtos.add(convertToDto(s));
		}
		return studentDtos;
	}
	
	
//	@RequestMapping("/students")
//	public List<StudentDto> getAllStudents(){
//		List<StudentDto> studentDtos = new ArrayList<>();
//		
//		for(Student s : service.getAllStudents()) {
//			studentDtos.add(convertToDto(s));
//		}
//		return studentDtos;
//	}
	
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
	
	@PostMapping(value = {"/student/{username}/{password}/{name}/{schoolName}", "/student/{username}/{password}/{name}/{schoolName}/"})
	public StudentDto registerStudent(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("name") String name, @PathVariable("schoolName") String schoolName){
		Student s = service.createStudent(username, password, name, schoolName);
		return convertToDto(s);
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
}