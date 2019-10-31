package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.TutoringSystemApplication;
import ca.mcgill.ecse321.tutoringsystem.dto.CourseDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RoomBookingDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RoomDto;
import ca.mcgill.ecse321.tutoringsystem.dto.StudentDto;
import ca.mcgill.ecse321.tutoringsystem.dto.StudentReviewDto;
import ca.mcgill.ecse321.tutoringsystem.dto.UniversityDto;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.model.RoomBooking;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.University;
import ca.mcgill.ecse321.tutoringsystem.service.TutoringSystemService;


@RestController
@CrossOrigin(origins = "*")
public class TutoringSystemRestController {
   
	@Autowired
	TutoringSystemService service;
	
	// <---------------------------------Register and LogIn------------------------------------->

//	<--------Get Mappings-------->
	
	
	//list of students registered
	@GetMapping(value = {"/students" , "/students/"})
	public List<StudentDto> getAllStudents(){
		List<StudentDto> studentDtos = new ArrayList<>();
		for(Student s : service.getAllStudents()) {
			studentDtos.add(convertToDto(s));
		}
		return studentDtos;
	}
// <-----Post Mappings------->

	
//register new student
	@PostMapping(value = {"/student/{username}/{password}/{name}", "/student/{username}/{password}/{name}/"})
	public StudentDto registerStudent(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("name") String name){
		Student student = service.getStudent(username);
		if(student!=null) {
			throw new IllegalArgumentException("Student with username already exists!");
		}
		Student s = service.createStudent(username, password, name);
		return convertToDto(s);
	}
	


// log in student
	@PostMapping(value = {"/student/{username}/{password}", "/student/{username}/{password}/"})
	public StudentDto loginStudent(@PathVariable("username") String username, @PathVariable("password") String password) {
		Student s = service.getStudent(username);
		if(s==null) throw new IllegalArgumentException("There is no such student!");
		String studentPass = s.getPassword();
		if(password.equals(studentPass)) {
		TutoringSystemApplication.setCurrentlyLoggedInStudent(s);
		return convertToDto(s);
		}else {
		throw new IllegalArgumentException("Incorrect Password!");
		}
	}

	//logout student
	@PutMapping(value = {"/logout", "/logout/"})
	public void logoutStudent() {
		if(TutoringSystemApplication.getCurrentlyLoggedInStudent() == null) throw new IllegalArgumentException("User not Logged In!");
		TutoringSystemApplication.setCurrentlyLoggedInStudent(null);
	}
	
	// <-----------------------------Searching for Courses ------------------------------>
	
	//<----Get Mappings---->
	
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
	@GetMapping(value= {"/course/{code}", "/course/{code}/"})
	public CourseDto getCourseByCourseCode(@PathVariable("code") String code){
		Course c = service.getCourse(code);
		if(c==null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		return convertToDto(c);
	}
	
	//search courses using universityName
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
	
	// <-----Post Mappings------->
	
	//add course using uni name
	@PostMapping(value = {"/course/{code}/{UniversityName}/{subject}","/course/{code}/{UniversityName}/{subject}/" })
	public CourseDto enterCourse(@PathVariable("code") String code, @PathVariable("UniversityName") String UniversityName, @PathVariable("subject") String subject) {
		
		University u = service.getUniversity(UniversityName);
		
		
		if(u==null)	throw new IllegalArgumentException("There is no such university!");
		
		Course c = service.createCourse(code, subject, u);
		return convertToDto(c);
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
	
	//enter university to system
	@PostMapping(value = {"/university/{id}/{name}", "/university/{id}/{name}/"})
	public UniversityDto enterUniversity(@PathVariable("id") int id, @PathVariable("name") String name){
		University u = service.createUniversity(id, name);
		UniversityDto UDto = convertToDto(u);
		return UDto;	
	}
	
	//register new student
		@PostMapping(value = {"/studentReview/{id}/{review}/{reviewerId}/{revieweeId}", "studentReview/{id}/{review}/{reviewerId}/{revieweeId}/"})
		public StudentReviewDto enterCourse(@PathVariable("id") int id, @PathVariable("review") String review, @PathVariable("reviewerId") String reviewerId, @PathVariable("revieweeId") String revieweeId) {
			
			Tutor t = service.getTutor(reviewerId);
			
			Student s = service.getStudent(revieweeId);
			
			if(t==null)	throw new IllegalArgumentException("There is no such tutor!");
			if(s==null)	throw new IllegalArgumentException("There is no such student!");
			
			StudentReview sr = service.createStudentReview(id, review, s, t);
			return convertToDto(sr);
		}
		//Create Room
		@PostMapping(value = {"/room/{roomNr}/{isLargeRoom}", "/room/{roomNr}/{isLargeRoom}"})
		public RoomDto createRoom(@PathVariable("roomNr") int roomNr, @PathVariable("isLargeRoom") boolean isLargeRoom) {
			Room r = service.createRoom(roomNr, isLargeRoom);
			return convertToDto(r);
		}
		
	// Create Room Booking
		@PostMapping(value = {"/room/createBooking/{roomNr}/{id}/{testDate}/{testStartTime}/{testEndTime}","/room/createBooking/{roomNr}/{id}/{testDate}/{testStartTime}/{testEndTime}"})
		public RoomBookingDto bookRoom(@PathVariable("roomNr") Integer roomNr, @PathVariable("id") Integer id,
												@PathVariable("testStartTime") Time testStartTime,
												@PathVariable("testDate") Date testDate, @PathVariable("testEndTime") Time testEndTime) {
			Room r = service.getRoom(roomNr);
			Set<RoomBooking> unavaiabilities = r.getUnavailability();
			for(RoomBooking session: unavaiabilities) {
				if(session.getDate() == testDate) {
					if(session.getStartTime().before(testStartTime) && session.getEndTime().after(testStartTime)) return null;
					if(session.getStartTime().before(testEndTime) && session.getEndTime().after(testEndTime)) return null;
				}
			}
			RoomBooking rb = service.createRoomBooking(id, testStartTime, testStartTime, testDate);
			return convertToDto(rb);
		}
	// Check availability	
		@GetMapping(value = {"/room/checkAvail/{roomNr}/{testDate}/{testStartTime}/{testEndTime}","/room/checkAvail/{roomNr}/testDate}/{testStartTime}/{testEndTime}"})
		public Boolean checkAvailability(@PathVariable("roomNr") Integer roomNr,@PathVariable("testStartTime") Time testStartTime,
												@PathVariable("testDate") Date testDate, @PathVariable("testEndTime") Time testEndTime) {
			Room r = service.getRoom(roomNr);
			Set<RoomBooking> unavaiabilities = r.getUnavailability();
			for(RoomBooking session: unavaiabilities) {
				if(session.getDate() == testDate) {
					if(session.getStartTime().before(testStartTime) && session.getEndTime().after(testStartTime)) return false;
					if(session.getStartTime().before(testEndTime) && session.getEndTime().after(testEndTime)) return false;
				}
			}
//			RoomBooking rb = service.createRoomBooking(id, testStartTime, testStartTime, testDate);
//			return convertToDto(rb);
			return true;
		}
	// <--------------------- DTOs ------------------>
	
	private StudentDto convertToDto(Student s) {
		if(s==null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		StudentDto sDto = new StudentDto();
		sDto.setUsername(s.getUsername());
		sDto.setName(s.getName());
		sDto.setPassword(s.getPassword());
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
	private StudentReviewDto convertToDto(StudentReview sr) {
		if(sr == null) {
			throw new IllegalArgumentException("There is no such student review!");
		}
		StudentReviewDto srDto = new StudentReviewDto(sr.getId(),sr.getReview(),sr.getAuthor(),sr.getReviewee());
		return srDto;
	}
	private RoomDto convertToDto(Room r) {
		if(r == null) {
			throw new IllegalArgumentException("No such room exists");
		}
		RoomDto rDto = new RoomDto(r.getRoomNr(), r.getIsLargeRoom(), r.getSession());
		return rDto;
	}
	private RoomBookingDto convertToDto(RoomBooking rb) {
		if(rb == null) {
			throw new IllegalArgumentException("No room booking exists");
		}
		RoomBookingDto rbDto = new RoomBookingDto(rb.getId(),rb.getDate(),rb.getStartTime(),rb.getEndTime());
		return rbDto;
	}
}