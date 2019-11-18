package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
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
import ca.mcgill.ecse321.tutoringsystem.dto.SessionDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RoomBookingDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RoomDto;
import ca.mcgill.ecse321.tutoringsystem.dto.StudentDto;
import ca.mcgill.ecse321.tutoringsystem.dto.StudentReviewDto;
import ca.mcgill.ecse321.tutoringsystem.dto.TutorDto;
import ca.mcgill.ecse321.tutoringsystem.dto.TutorReviewDto;
import ca.mcgill.ecse321.tutoringsystem.dto.UniversityDto;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.RoomBooking;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;

import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;
import ca.mcgill.ecse321.tutoringsystem.model.University;
import ca.mcgill.ecse321.tutoringsystem.service.TutoringSystemService;

@RestController
@CrossOrigin(origins = "*")
public class TutoringSystemRestController {

	@Autowired
	TutoringSystemService service;

	// <---------------------------------Register and
	// LogIn------------------------------------->

//	<--------Get Mappings-------->

	// list of students registered
	@GetMapping(value = { "/students", "/students/" })
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student s : service.getAllStudents()) {
			studentDtos.add(convertToDto(s));
		}
		return studentDtos;
	}
	
	@GetMapping(value = { "/student/{username}"})
	public StudentDto getStudent(@PathVariable("username") String username){
		Student s = service.getStudent(username);
		return convertToDto(s);
	}
// <-----Post Mappings------->

//register new student

	@PostMapping(value = { "/student/{username}/{password}/{name}", "/student/{username}/{password}/{name}/" })
	public StudentDto registerStudent(@PathVariable("username") String username,
			@PathVariable("password") String password, @PathVariable("name") String name) {

		if (service.getStudentByUsername(username) != null) {
			throw new IllegalArgumentException("Student with username already exists!");
		}

		Student s = service.createStudent(username, password, name);
		return convertToDto(s);
	}

// log in student
	@PostMapping(value = { "/student/{username}/{password}", "/student/{username}/{password}/" })
	public StudentDto loginStudent(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		Student s = service.getStudent(username);
		if (s == null)
			throw new IllegalArgumentException("There is no such student!");
		String studentPass = s.getPassword();
		if (password.equals(studentPass)) {
			TutoringSystemApplication.setCurrentlyLoggedInStudent(s);
			return (convertToDto(s));
		} else {
			throw new IllegalArgumentException("Incorrect Password!");
		}
	}

	@PutMapping(value = { "/logout", "/logout/" })
	public void logoutStudent() {
		if (TutoringSystemApplication.getCurrentlyLoggedInStudent() == null)
			throw new IllegalArgumentException("User not Logged In!");
		TutoringSystemApplication.setCurrentlyLoggedInStudent(null);
	}

	// <-----------------------------Searching for Courses
	// ------------------------------>

	// <----Get Mappings---->

	// get all courses
	@GetMapping(value = { "/courses", "/courses/" })
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courseDtos = new ArrayList<>();
		for (Course c : service.getAllCourses()) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;

	}

	// search by courseCode
	@GetMapping(value = { "/course/{code}", "/course/{code}/" })
	public CourseDto getCourseByCourseCode(@PathVariable("code") String code) {
		Course c = service.getCourse(code);
		if (c == null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		return convertToDto(c);
	}

	// search courses using universityName
	@GetMapping(value = { "/courses/{university}", "/courses/{university}" })
	public List<CourseDto> getCoursesForUniversity(@PathVariable("university") String university) {
		University u = service.getUniversity(university);
		List<CourseDto> courseDtos = new ArrayList<>();
		if (u == null) {
			throw new IllegalArgumentException("There is no such university!");
		}
		Set<Course> courses = u.getCourse();
		for (Course c : courses) {
			courseDtos.add(convertToDto(c));
		}
		return courseDtos;
	}

	// <-----Post Mappings------->

	// add course using uni name
	@PostMapping(value = { "/course/{code}/{UniversityName}/{subject}", "/course/{code}/{UniversityName}/{subject}/" })
	public CourseDto enterCourse(@PathVariable("code") String code,
			@PathVariable("UniversityName") String UniversityName, @PathVariable("subject") String subject) {

		University u = service.getUniversity(UniversityName);

		if (u == null)
			throw new IllegalArgumentException("There is no such university!");

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

	// enter university to system
	@PostMapping(value = { "/university/{id}/{name}", "/university/{id}/{name}/" })
	public UniversityDto enterUniversity(@PathVariable("id") int id, @PathVariable("name") String name) {
		University u = service.createUniversity(id, name);
		UniversityDto UDto = convertToDto(u);
		return UDto;
	}

	// <-------------- Search using subject category ------------>
	// Done by TR

	@GetMapping(value = { "/courses/subject/{sub}", "/courses/subject/{sub}" })
	public List<CourseDto> getCoursesForSubject(@PathVariable("sub") String sub) {

		List<CourseDto> courseDtos = new ArrayList<>();

		for (Course c : service.getAllCourses()) {

			if (c.getSubject().equals(sub)) {
				courseDtos.add(convertToDto(c));
			}

		}
		return courseDtos;
	}

	// <-------------- Tutor Profile ------------------>
	// Done by TR

	@GetMapping(value = { "/courses/tutors/{courseCode}", "/courses/tutors/{courseCode}/" })
	public List<TutorDto> getTutorsForCourse(@PathVariable("courseCode") String courseCode) {

		List<TutorDto> tutorDtos = new ArrayList<>();
		Course c = service.getCourse(courseCode);

		for (Tutor t : c.getTutor()) {
			tutorDtos.add(convertToDto(t));
		}

		return tutorDtos;

	}

	// <--------------- Manage Session ----------------->

	//student review
		@PostMapping(value = {"/studentReview/{id}/{review}/{reviewerId}/{revieweeId}", "studentReview/{id}/{review}/{reviewerId}/{revieweeId}/"})
		public StudentReviewDto enterCourse(@PathVariable("id") int id, @PathVariable("review") String review, @PathVariable("reviewerId") String reviewerId, @PathVariable("revieweeId") String revieweeId) {
			
			Tutor t = service.getTutor(reviewerId);
			
			Student s = service.getStudent(revieweeId);
			
			if(t==null)	throw new IllegalArgumentException("There is no such tutor!");
			if(s==null)	throw new IllegalArgumentException("There is no such student!");
			
			StudentReview sr = service.createStudentReview(id, review, s, t);
			return convertToDto(sr);
		}
		
		//create tutor review
		@PostMapping(value = {"/tutorReview/{id}/{review}/{revieweeId}/{rating}/{reviewerId}", "/tutorReview/{id}/{review}/{revieweeId}/{rating}/{reviewerId}/"})
		public TutorReviewDto createTutorReview(@PathVariable("id") int id, @PathVariable("review") String review, @PathVariable("reviewerId") String revieweeId, @PathVariable("rating") int rating,@PathVariable("revieweeId") String reviewerId) {


		  Tutor t = service.getTutor(reviewerId);
		  Student s = service.getStudent(revieweeId);


		  if(t==null)	throw new IllegalArgumentException("The tutor didn't exist!");
		  if(s==null)	throw new IllegalArgumentException("The student didn't exist!");

		  TutorReview tr = service.createTutorReview(id, review, t, rating, s);
		  return convertToDto(tr);
		}
	
	// <--------------------- Sessions ------------------>

	// get all sessions
	@GetMapping(value = { "/sessions", "/sessions/" })
	public List<SessionDto> getAllSessions() {
		List<SessionDto> sessionDtoList = new ArrayList<>();
		for (Session s : service.getAllSessions()) {
			sessionDtoList.add(convertToDto(s));
		}
		return sessionDtoList;
	}

	// get session with id
	@GetMapping(value = { "/sessions/{id}", "/sessions/{id}/" })
	public SessionDto getSessionWithId(@PathVariable("id") int id) {

		Session s = service.getSession(id);

		if (s == null) {
			throw new IllegalArgumentException("There is no such session!");
		}
		return convertToDto(s);
	}

/*	// creating a solo session and notifying the tutor
	@PostMapping(value = {
			"/session/{sessionId}/{tutorName}/{studentName}/{startTime}/{endTime}/{date}/{roomNr}/{courseCode}",
			"/session/{sessionId}/{tutorName}/{studentName}/{startTime}/{endTime}/{date}/{roomNr}/{courseCode}/" })
	public SessionDto enterSession(@PathVariable("sessionId") int sessionId,
			@PathVariable("tutorName") String tutorUsername, @PathVariable("studentName") String studentUsername,
			@PathVariable("startTime") Time startTime, @PathVariable("endTime") Time endTime,
			@PathVariable("date") Date date, @PathVariable("roomNr") int roomNr,
			@PathVariable("courseCode") String courseCode) {
		
		Tutor t = service.getTutor(tutorUsername);
		Student s = service.getStudent(studentUsername);
		Set<Student> studentSet = new HashSet<>();
		studentSet.add(s);
		Room r = service.getRoom(roomNr);
		Course c = service.getCourse(courseCode);
		

		Session ss = service.createSession(sessionId, false, startTime, endTime, date, false, studentSet, t, r, c);
		Set <Session> pendingSessions = t.getPendingSession();
		pendingSessions.add(ss);
		
		service.updateTutor(t.getUsername(), t.getName(), t.getPassword(), t.getHourlyRate(), pendingSessions, t.getSession());
		return convertToDto(ss);
	}	*/
	
	// creating a solo session and notifying the tutor
		@PostMapping(value = {
				"/session/{sessionId}/{tutorName}/{studentName}/{startTime}/{endTime}/{date}/{roomNr}/{courseCode}",
				"/session/{sessionId}/{tutorName}/{studentName}/{startTime}/{endTime}/{date}/{roomNr}/{courseCode}/" })
		public SessionDto enterSession(@PathVariable("sessionId") int sessionId,
				@PathVariable("tutorName") String tutorUsername, @PathVariable("studentName") String studentUsername,
				@PathVariable("startTime") String startTimeString, @PathVariable("endTime") String endTimeString,
				@PathVariable("date") String dateString, @PathVariable("roomNr") int roomNr,
				@PathVariable("courseCode") String courseCode) {
			
			
			
			Time startTime = Time.valueOf(startTimeString);
			Time endTime = Time.valueOf(endTimeString);
			Date date = Date.valueOf(dateString);
			
			
			Tutor t = service.getTutor(tutorUsername);
			Student s = service.getStudent(studentUsername);
			Set<Student> studentSet = new HashSet<>();
			studentSet.add(s);
			Room r = service.getRoom(roomNr);
			Course c = service.getCourse(courseCode);
			

			Session ss = service.createSession(sessionId, false, startTime, endTime, date, false, studentSet, t, r, c);
			Set <Session> pendingSessions = t.getPendingSession();
			pendingSessions.add(ss);
			
			service.updateTutor(t.getUsername(), t.getName(), t.getPassword(), t.getHourlyRate(), pendingSessions, t.getSession());
			return convertToDto(ss);
		}

	
	// confirm or decline session
	@PostMapping(value = { "/session/{sessionId}/{iscConfirmed}", "/notify/{sessionId}/{isConfirmed}/" })
	public SessionDto confirmSession(@PathVariable("sessionId") int sessionId,
			@PathVariable("isConfirmed") boolean isConfirmed) {

		service.updateSessionIsConfirmed(sessionId, isConfirmed);

		Session ss = service.getSession(sessionId);

		return convertToDto(ss);
	}
	
	// Cancel Session
	@PostMapping(value = {"/session/cancel/{sessionId}", "/session/cancel/{sessionId}/"})
	public void cancelSession(@PathVariable("sessionId") int sessionId) {
		service.deleteSession(sessionId);
	}

	// Create Room
	@PostMapping(value = { "/room/{roomNr}/{isLargeRoom}", "/room/{roomNr}/{isLargeRoom}" })
	public RoomDto createRoom(@PathVariable("roomNr") int roomNr, @PathVariable("isLargeRoom") boolean isLargeRoom) {
		Room r = service.createRoom(roomNr, isLargeRoom);
		return convertToDto(r);
	}

	// Get Room
	@GetMapping(value = { "/room/{roomNr}", "/room/{roomNr}" })
	public RoomDto getRoom(@PathVariable("roomNr") int roomNr) {
		Room r = service.getRoom(roomNr);
		return convertToDto(r);
	}
	
	// Assign Room to Session
	@PostMapping(value = {"/session/assign/{sessionId}", "/session/assign/{sessionId}/"})
	public SessionDto assignRoomToSession(@PathVariable("sessionId") int sessionId) {
		Session s = service.getSession(sessionId);
		
		Date sessionDate = s.getDate();
		Time sessionStartTime = s.getStartTime();
		Time sessionEndTime = s.getEndTime();
		boolean isGroupSession = s.getIsGroupSession();
		
		Date roomDate;
		Time roomStartTime;
		Time roomEndTime;
		boolean taken;
		if (isGroupSession) {
						
			for(Room r : service.getAllLargeRooms()) {
				Set<RoomBooking> bookings = r.getUnavailability();
				if (bookings == null || bookings.size() == 0) {
					
					Set<Session> roomSessions = r.getSession();
					roomSessions.add(s);
					int id = s.hashCode() * r.hashCode();
					RoomBooking booking = service.createRoomBooking(id, sessionStartTime, sessionEndTime, sessionDate);
					bookings.add(booking);
					
					service.updateRoom(r.getRoomNr(), r.getIsLargeRoom(), roomSessions, bookings);
					service.updateSession(id, s.getIsConfirmed(), s.getStartTime(), s.getEndTime(), s.getDate(), s.getIsGroupSession(), s.getStudent(), s.getTutor(), r, s.getCourse());
					break;
				}
				
				taken = false;
				for (RoomBooking rm : bookings) {
					if (rm.getDate() == sessionDate) {
						taken = true;
						break;
					}
					
					//session starts before booking start and ends after booking start
					if ((sessionStartTime.compareTo(rm.getStartTime()) <=0) && (sessionEndTime.compareTo(rm.getStartTime()) > 0)) {
						taken = true;
						break;
					}
					
					//session starts after booking start but before booking end
					if ((sessionStartTime.compareTo(rm.getStartTime())>= 0) && (sessionStartTime.compareTo(rm.getEndTime())<=0 )) {
						taken = true;
						break;
					}
				}
				//there are no bookings during the session's time
				if (!taken) {
					Set<Session> roomSessions = r.getSession();
					roomSessions.add(s);
					int id = s.hashCode() * r.hashCode();
					RoomBooking booking = service.createRoomBooking(id, sessionStartTime, sessionEndTime, sessionDate);
					bookings.add(booking);
					
					service.updateRoom(r.getRoomNr(), r.getIsLargeRoom(), roomSessions, bookings);
					service.updateSession(id, s.getIsConfirmed(), s.getStartTime(), s.getEndTime(), s.getDate(), s.getIsGroupSession(), s.getStudent(), s.getTutor(), r, s.getCourse());
					break;
				}
				
			}
			if (s.getRoom() == null) {
				throw new RuntimeException("Could not find an available room.");
			}
		} else {
			//when it's not a group session
			
			for(Room r : service.getAllSmallRooms()) {
				Set<RoomBooking> bookings = r.getUnavailability();
				if (bookings == null || bookings.size() == 0) {
					Set<Session> roomSessions = r.getSession();
					roomSessions.add(s);
					int id = s.hashCode() * r.hashCode();
					RoomBooking booking = service.createRoomBooking(id, sessionStartTime, sessionEndTime, sessionDate);
					bookings.add(booking);
					
					service.updateRoom(r.getRoomNr(), r.getIsLargeRoom(), roomSessions, bookings);
					service.updateSession(id, s.getIsConfirmed(), s.getStartTime(), s.getEndTime(), s.getDate(), s.getIsGroupSession(), s.getStudent(), s.getTutor(), r, s.getCourse());
					break;
				}
				
				taken = false;
				for (RoomBooking rm : bookings) {
					if (rm.getDate() == sessionDate) {
						taken = true;
						break;
					}
					
					//session starts before booking start and ends after booking start
					if ((sessionStartTime.compareTo(rm.getStartTime()) <=0) && (sessionEndTime.compareTo(rm.getStartTime()) > 0)) {
						taken = true;
						break;
					}
					
					//session starts after booking start but before booking end
					if ((sessionStartTime.compareTo(rm.getStartTime())>= 0) && (sessionStartTime.compareTo(rm.getEndTime())<=0 )) {
						taken = true;
						break;
					}
				}
				//there are no bookings during the session's time
				if (!taken) {
					Set<Session> roomSessions = r.getSession();
					roomSessions.add(s);
					int id = s.hashCode() * r.hashCode();
					RoomBooking booking = service.createRoomBooking(id, sessionStartTime, sessionEndTime, sessionDate);
					bookings.add(booking);
					
					service.updateRoom(r.getRoomNr(), r.getIsLargeRoom(), roomSessions, bookings);
					service.updateSession(id, s.getIsConfirmed(), s.getStartTime(), s.getEndTime(), s.getDate(), s.getIsGroupSession(), s.getStudent(), s.getTutor(), r, s.getCourse());
					break;
				}
				
			}
			if (s.getRoom() == null) {
				throw new RuntimeException("Could not find an available room.");
			}
		}
		
		return convertToDto(s);
	}
	

	// Create Room Booking
	@PostMapping(value = { "/room/createBooking/{roomNr}/{id}/{testDate}/{testStartTime}/{testEndTime}",
			"/room/createBooking/{roomNr}/{id}/{testDate}/{testStartTime}/{testEndTime}" })
	public RoomBookingDto bookRoom(@PathVariable("roomNr") Integer roomNr, @PathVariable("id") Integer id,
			@PathVariable("testStartTime") Time testStartTime, @PathVariable("testDate") Date testDate,
			@PathVariable("testEndTime") Time testEndTime) {
		Room r = service.getRoom(roomNr);
		Set<RoomBooking> unavaiabilities = r.getUnavailability();
		for (RoomBooking session : unavaiabilities) {
			if (session.getDate() == testDate) {
				if (session.getStartTime().before(testStartTime) && session.getEndTime().after(testStartTime))
					return null;
				if (session.getStartTime().before(testEndTime) && session.getEndTime().after(testEndTime))
					return null;
			}

			RoomBooking rb = service.createRoomBooking(id, testStartTime, testStartTime, testDate);
			return convertToDto(rb);	

		}
		RoomBooking rb = service.createRoomBooking(id, testStartTime, testStartTime, testDate);
		return convertToDto(rb);
	}

	// Check availability
	@GetMapping(value = { "/room/checkAvail/{roomNr}/{testDate}/{testStartTime}/{testEndTime}",
			"/room/checkAvail/{roomNr}/testDate}/{testStartTime}/{testEndTime}" })
	public Boolean checkAvailability(@PathVariable("roomNr") Integer roomNr,
			@PathVariable("testStartTime") Time testStartTime, @PathVariable("testDate") Date testDate,
			@PathVariable("testEndTime") Time testEndTime) {
		Room r = service.getRoom(roomNr);
		Set<RoomBooking> unavaiabilities = r.getUnavailability();
		for (RoomBooking session : unavaiabilities) {
			if (session.getDate() == testDate) {
				if (session.getStartTime().before(testStartTime) && session.getEndTime().after(testStartTime))
					return false;
				if (session.getStartTime().before(testEndTime) && session.getEndTime().after(testEndTime))
					return false;
			}
		}
//			RoomBooking rb = service.createRoomBooking(id, testStartTime, testStartTime, testDate);
//			return convertToDto(rb);
		return true;
	}

	// Request course
	@PostMapping(value = {"/course/request/{courseCode}/{subject}/{universityName}", "/course/request/{courseCode}/{subject}/{universityName}/"})
	public CourseDto requestCourse(@PathVariable("courseCode") String courseCode,
			@PathVariable("subject") String subject, @PathVariable("universityName") String universityName) {
		
		Course c = service.getCourse(courseCode);
		if (c != null) {
			throw new IllegalArgumentException("Course already exists.");
		}
		University u = service.getUniversity(universityName);
		
		Course course = service.createCourse(courseCode, subject, u);
		course = service.updateCourse(courseCode, subject, u, true);
		
		//c.setIsRequested(true);
		return convertToDto(course);
	}
	
	
	//post tutor
	
	// createTutor
		@PostMapping(value = { "/tutor/{username}/{password}/{name}/{hourlyrate}", "/tutor/{username}/{password}/{name}/{hourlyrate}/" })
		public TutorDto registerTutor(@PathVariable("username") String username,
				@PathVariable("password") String password, @PathVariable("name") String name, @PathVariable("hourlyrate") Double hourlyRate) {

			Tutor t = service.createTutor(name, username, password, hourlyRate);
			return convertToDto(t);
		}
	 //
		@GetMapping(value= {"/tutor", "/tutor/"})
		public List<TutorDto> getAllTutors(){
			List<TutorDto> tutors = new ArrayList<>();
			
			for(Tutor tutor : service.getAllTutors()) {
				TutorDto tDto = convertToDto(tutor);
				tutors.add(tDto);
			}
			
			return tutors;
		}
	
	// <--------------------- DTOs ------------------>
	private StudentDto convertToDto(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		StudentDto sDto = new StudentDto(s.getUsername(), s.getPassword(), s.getName());
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
		if (c == null) {
			throw new IllegalArgumentException("There is no such course!");
		}
		CourseDto cDto = new CourseDto(c.getCourseCode(), c.getSubject(), c.getUniversity());
		return cDto;
	}

	private TutorDto convertToDto(Tutor t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such tutor!");
		}
		TutorDto tDto = new TutorDto(t.getName(), t.getHourlyRate(), t.getStudentReview(), t.getCourse());
		return tDto;
	}

/*	private SessionDto convertToDto(Session s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such session!");
		}
		SessionDto sDto = new SessionDto(s.getId(), s.getIsConfirmed(), s.getStartTime(), s.getEndTime(), s.getDate(),
				s.getIsGroupSession(), s.getStudent(), s.getTutor(), s.getRoom(), s.getCourse());
		return sDto;
	} */
	
	private SessionDto convertToDto(Session s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such session!");
		}
		Set<Student> studentSet = s.getStudent();
		Set<String> studentNames = new HashSet<>();
		
		for (Student temp : studentSet) {
			studentNames.add(temp.getName());
		}
		
		
		SessionDto sDto = new SessionDto(s.getId(), studentNames, s.getTutor().getName(), s.getRoom().getRoomNr(), s.getCourse().getCourseCode(), s.getDate(), s.getStartTime(), s.getEndTime(), s.getIsGroupSession(), s.getIsConfirmed() );
		return sDto;
	}

	private StudentReviewDto convertToDto(StudentReview sr) {
		if (sr == null) {
			throw new IllegalArgumentException("There is no such student review!");
		}
		StudentReviewDto srDto = new StudentReviewDto(sr.getId(), sr.getReview(), sr.getAuthor(), sr.getReviewee());
		return srDto;
	}

	private RoomDto convertToDto(Room r) {
		if (r == null) {
			throw new IllegalArgumentException("No such room exists");
		}
		RoomDto rDto = new RoomDto(r.getRoomNr(), r.getIsLargeRoom(), r.getSession());
		return rDto;
	}

	private TutorReviewDto convertToDto(TutorReview tr) {
 		if(tr == null) {
 			throw new IllegalArgumentException("There is no such Tutor review!");
 		}
 		TutorReviewDto trDto = new TutorReviewDto(tr.getId(), tr.getReview(), tr.getReviewee(), tr.getRating(), tr.getAuthor());
 		return trDto;
 	}

	private RoomBookingDto convertToDto(RoomBooking rb) {
		if (rb == null) {
			throw new IllegalArgumentException("No room booking exists");
		}
		RoomBookingDto rbDto = new RoomBookingDto(rb.getId(), rb.getDate(), rb.getStartTime(), rb.getEndTime());
		return rbDto;
	}
	
	// get all sessions
			@GetMapping(value = { "/currentsesh", "/currentsesh/" })
			public List<SessionDto> getAllCurrentSessions() {
				
				Student currentStudent = TutoringSystemApplication.getCurrentlyLoggedInStudent();
				
				List<SessionDto> sessionDtoList = new ArrayList<>();
				boolean contains = false;
				
				for (Session s : service.getAllSessions()) {		
					
					for(Student std : s.getStudent()) {
						if(std.getUsername().equals(currentStudent.getUsername())){
							contains = true;
						}
					}
					if (contains) {
						sessionDtoList.add(convertToDto(s));
					}
				}
				return sessionDtoList;
			}

}