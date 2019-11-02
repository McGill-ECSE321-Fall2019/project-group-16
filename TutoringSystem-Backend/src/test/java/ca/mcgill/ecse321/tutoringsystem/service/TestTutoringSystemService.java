package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
//import ca.mcgill.ecse321.tutoringsystem.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomBookingRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.UniversityRepository;
//import ca.mcgill.ecse321.tutoringsystem.dao.UserRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.model.RoomBooking;
import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.University;
//import ca.mcgill.ecse321.tutoringsystem.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringSystemService {

	@Autowired
	private TutoringSystemService service;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentReviewRepository studentReviewRepository;
	
	@Autowired
	private TutorReviewRepository tutorReviewRepository;
	
	@Autowired
	private RoomBookingRepository roomBookingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private UniversityRepository universityRepository;
	
	/*
	 * @Autowired private UserRepository userRepository;
	 */
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@After
	public void clearDatabase() {
		sessionRepository.deleteAll();
		studentReviewRepository.deleteAll();
		tutorReviewRepository.deleteAll();
		roomBookingRepository.deleteAll();
		roomRepository.deleteAll();
		courseRepository.deleteAll();
		universityRepository.deleteAll();
		studentRepository.deleteAll();
		tutorRepository.deleteAll();
		
	}
	//Student Tests
	
	@Test
	public void testCreateStudent() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(username, allStudents.get(0).getUsername());
		assertEquals(password, allStudents.get(0).getPassword());
	}
	
	@Test
	public void testUpdateStudent() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		
		password = "newStudentPassword";
		name = "Jeff";
		try {
			service.updateStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		allStudents = service.getAllStudents();
		
		assertEquals(username, allStudents.get(0).getUsername());
		assertEquals(password, allStudents.get(0).getPassword());
		assertEquals(name, allStudents.get(0).getName());
	}
	
	@Test
	public void testGetStudentUserNameNull() {
		
		String error = "";
		try {
		Student s = service.getStudent(null);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Username can't be empty.",error);
		
	}
	
	@Test
	public void testGetStudentUserNameNotExisting() {
		
		String error = "";
		try {
		Student s = service.getStudent("rich");
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("User does not exist.",error);
		
	}
	
	@Test
	public void testUpdateStudentNull() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		
		name = null;
		username = null;
		password = null;
		
		try {
			service.updateStudent(username, password, name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Username can't be empty. Password can't be empty. New name can't be empty.", e.getMessage().trim());
		}
	}
	@Test
	public void testUpdateNonStudent() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		
		name = "This";
		username = "YeeHaw";
		password = "Nice";
		
		try {
			service.updateStudent(username, password, name);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Please input a valid student.", e.getMessage().trim());
		}
	}
	
	@Test
	public void testDeleteStudent() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		//String schoolName = "McGill";

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		try {
			service.deleteStudent(username);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullUsername() {

		String name = "richard";
		String username = null;
		String password = "studentPassword1";
		
		String error = null;

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Username can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullPassword() {

		String name = "richard";
		String username = "student1";
		String password = null;
	
		String error = null;

		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Password can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testGetStudentByUsername() {
		String name = "richard";
		String username = "student1";
		String password = "password";
		String error = null;
		
		try {
			service.createStudent(username, password, name);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}
		
        Student s = new Student();
		try {
			s = service.getStudentByUsername(username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(username, s.getUsername());
	}
	
	//Tutor tests
	@Test
	public void testCreateTutor() {
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;

		try {
			service.createTutor(name,username, password, rate);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(1, allTutors.size());
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(username, allTutors.get(0).getUsername());
		assertEquals(password, allTutors.get(0).getPassword());
		assertEquals(rate, allTutors.get(0).getHourlyRate(), 0.01);
	}
	
	
	@Test
	public void testUpdateTutor() {

		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Set<Session> sessions = new HashSet<Session>();
		Set<Session> pendingSessions = new HashSet<Session>();

		try {
			service.createTutor(name, username, password,rate);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(1, allTutors.size());
		
		name = "Michael";
		password = "newTutorpass";
		rate  = 14;
		
		try {
			service.updateTutor(username, name, password, rate, pendingSessions, sessions);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		allTutors = service.getAllTutors();
		
		assertEquals(username, allTutors.get(0).getUsername());
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(password, allTutors.get(0).getPassword());
		assertEquals(rate, allTutors.get(0).getHourlyRate(), 0.01);
		
	}
	
	@Test
	public void testUpdateNullTutor() {

		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;

		Set<Session> sessions = new HashSet<Session>();
		Set<Session> pendingSessions = new HashSet<Session>();

		try {
			service.createTutor(name, username, password,rate);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(1, allTutors.size());
		

		name = null;
		password = null;
		rate  = -14;
		username = null;
		
		try {
			service.updateTutor(username, name, password, rate, pendingSessions, sessions);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Username can't be empty. Password can't be empty. New name can't be empty. Hourly rate is invalid.", e.getMessage().trim());
		}
	}
	
	@Test
	public void testDeleteTutor() {

		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;

		try {
			service.createTutor(name, username, password, rate);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		
		try {
			service.deleteTutor(username);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	
	@Test
	public void testCreateTutorNullName() {

		String name = null;
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		String error = null;

		try {
			service.createTutor(name, username, password, rate);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Name can't be empty.", error.trim());
		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	
	@Test
	public void testCreateTutorNullUsername() {

		String name = "Joseph";
		String username = null;
		String password = "tutorPassword1";
		double rate = 18;
		
		String error = null;

		try {
			service.createTutor(name, username, password, rate);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Username can't be empty.", error.trim());
		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	
	@Test
	public void testCreateTutorNullPassword() {

		String name = "Joseph";
		String username = "tutor1";
		String password = null;
		double rate = 18;
		
		String error = null;

		try {
			service.createTutor(name, username, password, rate);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Password can't be empty.", error.trim());
		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	

	@Test
	public void testCreateTutorInvalidHR() {

		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = -18;
		
		String error = null;

		try {
			service.createTutor(name, username, password, rate);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Hourly rate is invalid", error.trim());
		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	
	//University tests
	
	@Test
	public void testCreateUniversity() {

		String name= "McGill University";
		int id = 1;

		try {
			service.createUniversity(id, name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<University> allUniversities = service.getAllUniversities();

		assertEquals(1, allUniversities.size());
		assertEquals(name, service.getUniversity(name).getName());	
		}
	
    @Test
    public void testGetUniversityNullName() {
    		
    		String error = "";
    		try {
    			service.getUniversity(null);
    		}catch (IllegalArgumentException e) {
    		  error = e.getMessage();
    		}
    		assertEquals("Name can't be empty. ", error);
    	
    }  
	@Test
	public void testUpdateUniversity() {

		String name = "McGill University";
		int id = 1;
		
		try {
			service.createUniversity(id,name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<University> allUniversities = service.getAllUniversities();
		for (University u : allUniversities) {
			System.out.println(u.getId());
		}

		assertEquals(1, allUniversities.size());
		
		String newName = "Concordia University";
	
		try {
			service.updateUniversity(id, newName);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		allUniversities = service.getAllUniversities();
		
		assertEquals(1, allUniversities.size());
		assertEquals(newName, allUniversities.get(0).getName());
		
	}
	
	@Test
	public void testCreateUniversityNullName() {
		String name= null;
		int id = 1;
		String error = null;
		try {
			service.createUniversity(id,name);
		} catch (IllegalArgumentException e) { 
			error = e.getMessage();
		}

		assertEquals("Name can't be empty.", error.trim());
		List<University> allUniversities = service.getAllUniversities();
		assertEquals(0, allUniversities.size());
		
		}
	
	
	// <----StudentReview---->
	
	@Test
	public void testCreateStudentReview() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		int id = 1;
		String comments = "Good Student, listens well!";
		
		Tutor reviewer = service.createTutor(username,password, name, 20);
		
		try {
			service.createStudentReview(id,comments,reviewee, reviewer);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<StudentReview> allReviews = service.getAllStudentReviews();
        
		assertEquals(1, allReviews.size());
		assertEquals(id, service.getStudentReview(id).getId(), 0);
		assertEquals(comments, allReviews.get(0).getReview());
		assertEquals(username, allReviews.get(0).getReviewee().getUsername());
	}
	
	@Test
	public void testUpdateStudentReview() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		Tutor reviewer = service.createTutor(username,password, name, 20);
		int id = 1;
		String comments = "Good Student, listens well!";
		

		try {
			service.createStudentReview(id,comments,reviewee, reviewer);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<StudentReview> allReviews = service.getAllStudentReviews();
        
		assertEquals(1, allReviews.size());
		
		comments = "Pain in the ass";
		
		try {
			service.updateStudentReview(id, comments, reviewee);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		
	}
	@Test
	public void testUpdateStudentReviewNull() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		Tutor reviewer = service.createTutor(username,password, name, 20);
		int id = 1;
		String comments = "Good Student, listens well!";
		

		try {
			service.createStudentReview(id,comments,reviewee, reviewer);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<StudentReview> allReviews = service.getAllStudentReviews();
        
		assertEquals(1, allReviews.size());
		
		comments = "";
		id = -1;
		reviewee = null;
		
		try {
			service.updateStudentReview(id, comments, reviewee);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incorrect id value. Please insert a reviewee. Please insert a comment.", e.getMessage().trim());
		}	
	}
	
	@Test
	public void testUpdateStudentReviewInvalid() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		Tutor reviewer = service.createTutor(username,password, name, 20);
		int id = 1;
		String comments = "Good Student, listens well!";
		

		try {
			service.createStudentReview(id,comments,reviewee, reviewer);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<StudentReview> allReviews = service.getAllStudentReviews();
        
		assertEquals(1, allReviews.size());

		id = 100;
		
		try {
			service.updateStudentReview(id, comments, reviewee);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Please enter a valid Review to update.", e.getMessage().trim());
		}	
	}
	
	@Test
	public void testCreateStudentReviewNullReviewee() {

		Student reviewee = null;
		int id = 1;
		String comments = "Good Student, listens well!";
		
		Tutor reviewer = service.createTutor("Jeff1","mynameJeff", "Jeffery", 20);
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee, reviewer);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("Reviewee is null.", error.trim());

		List<StudentReview> allReviews = service.getAllStudentReviews();

		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateStudentReviewNullComments() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		Tutor reviewer = service.createTutor(username,password, name, 20);
		
		int id = 1;
		String comments = null;
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee, reviewer);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("Comments can't be empty.", error.trim());

		List<StudentReview> allReviews = service.getAllStudentReviews();

		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateStudentReviewInvalidId() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student reviewee = service.createStudent(username, password, name);
		Tutor reviewer = service.createTutor(username,password, name, 20);
		int id = -1;
		String comments = "good student";
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee, reviewer);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("ID is invalid.", error.trim());

		List<StudentReview> allReviews = service.getAllStudentReviews();

		assertEquals(0, allReviews.size());
	}
	
// <-----TutorReview----->
	
	@Test
	public void testCreateTutorReview() {
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor reviewee = service.createTutor(name, username, password, rate);
		Student reviewer = service.createStudent(username, password, name);
		int id = 1;
		String comments = "Good Student, listens well!";
		int rating = 4;
		List<Tutor> allTutors = service.getAllTutors();
		assertEquals(1, allTutors.size());
		try {
			service.createTutorReview(id,comments,reviewee, rating,reviewer);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<TutorReview> allReviews = service.getAllTutorReviews();
        
		assertEquals(1, allReviews.size());
		assertEquals(id, service.getTutorReview(id).getId(), 0);
		assertEquals(comments, allReviews.get(0).getReview());
		assertEquals(username, allReviews.get(0).getReviewee().getUsername());
	}
	@Test
	public void testCreateTutorReviewNullReviewee() {
		
		Tutor reviewee = null;
		int id = 1;
		String comments = "Good Student, listens well!";
		int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating, null);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<TutorReview> allReviews = service.getAllTutorReviews();
        
		assertEquals("Reviewee is null. Reviewer is null.", error.trim());


		assertEquals(0, allReviews.size());
	}
	
	
	@Test
	public void testCreateTutorReviewNullComments() {
		
	
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
	Student reviewer = service.createStudent(username, password, name);
	int id = 1;
	String comments = null;
	int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating,reviewer);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<TutorReview> allReviews = service.getAllTutorReviews();
        
		assertEquals("Comments can't be empty.", error.trim());


		assertEquals(0, allReviews.size());
	}
	

	@Test
	public void testCreateTutorReviewInvalidId() {
		
	
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
	Student reviewer = service.createStudent(username, password, name);
    int id = -1;
	String comments = "Good Student, listens well!";
	int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating, reviewer);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<TutorReview> allReviews = service.getAllTutorReviews();
        
		assertEquals("ID is invalid.", error.trim());


		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateTutorReviewInvalidRating() {
		
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
	Student reviewer = service.createStudent(username, password, name);
    int id = 1;
	String comments = "Good Student, listens well!";
	int rating = 10;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating, reviewer);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<TutorReview> allReviews = service.getAllTutorReviews();
        
		assertEquals("Rating is invalid.", error.trim());


		assertEquals(0, allReviews.size());
	}
	
	// <---------Session--------->
	
	@Test
	public void testCreateSession() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1, "McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Session> allSessions = service.getAllSessions();

		assertEquals(1, allSessions.size());
		assertEquals(id, allSessions.get(0).getId(), 0);
		assertEquals(isConfirmed, allSessions.get(0).getIsConfirmed());
		assertEquals(startTime, allSessions.get(0).getStartTime());
		assertEquals(endTime, allSessions.get(0).getEndTime());
		assertEquals(date, allSessions.get(0).getDate());
		assertEquals(isGroupSession, allSessions.get(0).getIsGroupSession());
		assertEquals(course.getCourseCode(), allSessions.get(0).getCourse().getCourseCode());
		assertEquals(room.getRoomNr(), allSessions.get(0).getRoom().getRoomNr());
		assertEquals(tutor.getUsername(), allSessions.get(0).getTutor().getUsername());
	}
	
	@Test
	public void testCreateSessionNullStartTime() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = null;
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1,"McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Start time is invalid.", error.trim());


		assertEquals(0, allSessions.size());
	}
	
	@Test
	public void testCreateSessionNullEndTime() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = null;
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1, "McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("End time is invalid.", error.trim());

		assertEquals(0, allSessions.size());
		
	}
	
	@Test
	public void testCreateSessionNullDate() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = null;
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1, "McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
			
		assertEquals("Date is null.", error.trim());

		assertEquals(0, allSessions.size());
		
	}
	
	@Test
	public void testCreateSessionNullCourse() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
       
        Room room = service.createRoom(1, false);
		Course course = null;
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Course is null.", error.trim());

		assertEquals(0, allSessions.size());
		
	}
	
	@Test
	public void testCreateSessionNullRoom() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
	
		name = "richard";
		username = "student1";
		password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1,"McGill University");
        Room room = null;
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Room number is invalid. Room is null.", error.trim());

		assertEquals(0, allSessions.size());
		
	}
	
	@Test
	public void testCreateSessionNullStudent() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		String name = "Joseph";
		String username = "tutor1";
		String password = "tutorPassword1";
		double rate = 18;
		
		Tutor tutor = service.createTutor(name, username, password, rate);
		
		Student student = null;
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1,"McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Tutee is null.", error.trim());

		assertEquals(0, allSessions.size());
		
	}
	
	@Test
	public void testCreateSessionNullTutor() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2019-10-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("14:00:00");
		
		
		Tutor tutor = null;
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		
		Student student = service.createStudent(username, password, name);
		Set <Student> students = new HashSet<>();
		students.add(student);

		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity(1,"McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Tutor is null.", error.trim());

		assertEquals(0, allSessions.size());
		
		
	}
	
	
	//---------- Room Class Tests --------------	
	
		@Test
		public void testCreateRoom() {
			assertEquals(0, service.getAllRooms().size());
			
			int roomNumber = 1;
			Boolean isLargeRoom = true;
			
			try {
				service.createRoom(roomNumber, isLargeRoom);
			} catch (IllegalArgumentException e) {
				fail();
			}
			
			List<Room> allRooms = service.getAllRooms();
			
			assertEquals(1, allRooms.size());
			assertEquals(roomNumber, (int) allRooms.get(0).getRoomNr());
			assertEquals(isLargeRoom, allRooms.get(0).getIsLargeRoom());
			
				
		}
		
		@Test
		public void testDeleteRoom() {
			assertEquals(0, service.getAllRooms().size());
			
			int roomNr = 1;
			Boolean isLargeRoom = true;
			
			try {
				service.createRoom(roomNr, isLargeRoom);
			}
			catch(IllegalArgumentException e) {
				fail();
			}
			
			try {
				service.deleteRoom(roomNr);
			}
			catch(IllegalArgumentException e) {
				fail();
			}
			
			List<Room> allRooms = service.getAllRooms();
			assertEquals(0, allRooms.size());
			
		}
		
		@Test
		public void testUpdateRoomSize() {
			assertEquals(0, service.getAllRooms().size());
			
			int roomNumber = 1;
			Boolean isLargeRoom  = true;
			

			try {
				service.createRoom(roomNumber, isLargeRoom);
			} catch (IllegalArgumentException e) {
				
				fail();
			}

			List<Room> allRooms = service.getAllRooms();

			assertEquals(1, allRooms.size());
			assertEquals(roomNumber, (int) allRooms.get(0).getRoomNr());
			assertEquals(isLargeRoom, allRooms.get(0).getIsLargeRoom());

			Boolean newIsLargeRoom = false; //MAY CAUSE ISSUES
			
			try {
				service.updateRoomSize(roomNumber, newIsLargeRoom);
			} catch (IllegalArgumentException e) {
				fail();
			}

			allRooms = service.getAllRooms();
			assertEquals(1, allRooms.size());
			assertEquals(roomNumber, (int) allRooms.get(0).getRoomNr());
			assertEquals(newIsLargeRoom, allRooms.get(0).getIsLargeRoom());
			
		}
		@Test
		public void testUpdateInvalidRoomSize() {
			assertEquals(0, service.getAllRooms().size());
			
			int roomNumber = 1;
			Boolean isLargeRoom  = true;
			

			try {
				service.createRoom(roomNumber, isLargeRoom);
			} catch (IllegalArgumentException e) {
				
				fail();
			}

			List<Room> allRooms = service.getAllRooms();

			assertEquals(1, allRooms.size());
			assertEquals(roomNumber, (int) allRooms.get(0).getRoomNr());
			assertEquals(isLargeRoom, allRooms.get(0).getIsLargeRoom());
			
			try {
				service.updateRoomSize(-1, null);
				fail();
			} catch (IllegalArgumentException e) {
				assertEquals("Room number is invalid. Room Type (isLarge = true || false) cannot be null. ", e.getMessage());
			}
			
		}
		@Test
		public void testCreateRoomInvalidID() {

			int roomNumber = -1;
			Boolean isLargeRoom = true;
		
			String error = null;

			try {
				service.createRoom(roomNumber, isLargeRoom);
			} catch (IllegalArgumentException e) {
				
				error = e.getMessage();
			}

			assertEquals("Room number is invalid.", error.trim());
			List<Room> allRooms = service.getAllRooms();

			assertEquals(0, allRooms.size());

		}
		
		@Test
		public void testCreateRoomNullIsLarge() {

			int roomNumber = 1;
			Boolean isLargeRoom = null;
		
			String error = null;

			try {
				service.createRoom(roomNumber, isLargeRoom);
			} catch (IllegalArgumentException e) {
				
				error = e.getMessage();
			}

			assertEquals("Room Type (isLarge = true || false) cannot be null.", error.trim());
			List<Room> allRooms = service.getAllRooms();

			assertEquals(0, allRooms.size());

		}
		

		// -----------------Course Class Tests------------------
			
			@Test
			public void testCreateCourse() {
						
				String courseCode = "ECSE321";
				String subject = "SoftwareEng";
				University university =  service.createUniversity(1,"McGill");
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					fail();
				}
				
				List<Course> allCourses = service.getAllCourses();
				
				assertEquals(1, allCourses.size());
				assertEquals(courseCode, allCourses.get(0).getCourseCode());
				assertEquals(subject, allCourses.get(0).getSubject());
				assertEquals(university.getId(), allCourses.get(0).getUniversity().getId());
					
			}
			
			@Test
			public void testUpdateCourse() {
				
				String courseCode = "ECSE321";
				String subject = "SoftwareEng";
				University university =  service.createUniversity(1,"McGill");
				Boolean flag = true;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					fail();
				}
				
				List<Course> allCourses = service.getAllCourses();
				
				assertEquals(1, allCourses.size());
				assertEquals(courseCode, allCourses.get(0).getCourseCode());
				assertEquals(subject, allCourses.get(0).getSubject());
				assertEquals(university.getId(), allCourses.get(0).getUniversity().getId());
				
				String newSubject = "Science";
				University newUniversity = service.createUniversity(1,"Concordia");
				
				try {
					service.updateCourse(courseCode, newSubject, newUniversity, flag);
				} catch (IllegalArgumentException e) {
					fail();
				}
				
				allCourses = service.getAllCourses();
				
				assertEquals(1, allCourses.size());
				assertEquals(courseCode, allCourses.get(0).getCourseCode());	
				assertEquals(newSubject, allCourses.get(0).getSubject());
				assertEquals(newUniversity.getId(), allCourses.get(0).getUniversity().getId());
				assertEquals(flag, allCourses.get(0).getIsRequested());
				
			}
			@Test
			public void testUpdateCourseInvalid() {
				String courseCode = "ECSE321";
				String subject = "SoftwareEng";
				University university =  service.createUniversity(1,"McGill");
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					fail();
				}
				
				List<Course> allCourses = service.getAllCourses();
				
				assertEquals(1, allCourses.size());
				assertEquals(courseCode, allCourses.get(0).getCourseCode());
				assertEquals(subject, allCourses.get(0).getSubject());
				assertEquals(university.getId(), allCourses.get(0).getUniversity().getId());
				
				String newSubject = "";
				University newUniversity = null;
				Boolean flag = null;
				
				try {
					service.updateCourse("", newSubject, newUniversity, flag);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Please provide a courseCode. Please provide a subject. University cannot be null. Must be requested or not. ", e.getMessage());
				}
			}
			
			
			
			@Test
			public void testUpdateCourseNonExistant() {
				University u = service.createUniversity(1, "McGill");
				try {
					service.updateCourse("FakeCourse", "YeeHaw", u, true);
					fail();
				} catch(IllegalArgumentException e) {
					assertEquals("The course does not exist. Please specify a valid Course Code", e.getMessage());
				}
			}
						
			@Test
			public void testCreateCourseNullCourseCode() {
				
				String courseCode = null;
				String subject = "Science";
				University university = service.createUniversity(1,"McGill");
				
				String error = null;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Please provide a courseCode.", error.trim());
				List<Course> allCourses = service.getAllCourses();
				assertEquals(0, allCourses.size());
				
				
			}
			
			@Test
			public void testCreateCourseNullSubject() {
				
				String courseCode = "ECSE321";
				String subject = null;
				University university = service.createUniversity(1,"McGill");
				
				String error = null;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Please provide a subject.", error.trim());
				List<Course> allCourses = service.getAllCourses();
				assertEquals(0, allCourses.size());
				
				
			}
			
			@Test
			public void testCreateCourseNullUniversity() {
				
				String courseCode = "ECSE321";
				String subject = "Science";
				University university = null;
				
				String error = null;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("University cannot be null.", error.trim());
				List<Course> allCourses = service.getAllCourses();
				assertEquals(0, allCourses.size());
				
				
			}
			
			

		// -----------------Room Booking Class Tests------------------
			
			@Test
			public void testCreateRoomBooking() {
				assertEquals(0, service.getAllRoomBookings().size());
				
				//long millis=System.currentTimeMillis();
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e){
					fail();
				}
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(1, allRoomBookings.size());
				assertEquals(id, allRoomBookings.get(0).getId());
				assertEquals(startTime, allRoomBookings.get(0).getStartTime());
				assertEquals(endTime, allRoomBookings.get(0).getEndTime());
				assertEquals(date, allRoomBookings.get(0).getDate());
				
			}
			
			//DELETE ROOM BOOKING METHOD NEEDS TO BE IMPLEMENTED
			
			@Test
			public void testUpdateRoomBooking() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e){
					fail();
				}
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(1, allRoomBookings.size());
				assertEquals(id, allRoomBookings.get(0).getId());
				assertEquals(startTime, allRoomBookings.get(0).getStartTime());
				assertEquals(endTime, allRoomBookings.get(0).getEndTime());
				assertEquals(date, allRoomBookings.get(0).getDate());
				
				startTime = Time.valueOf("15:00:00");
				endTime = Time.valueOf("16:00:00");
				date = Date.valueOf("2019-10-20");
				
				try {
					service.updateRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e) {
					fail();	
				}
				//Test getRoomBooking
				
				RoomBooking roomBooking = service.getRoomBooking(id);
				
				assertEquals(id, roomBooking.getId());
				assertEquals(startTime, roomBooking.getStartTime());
				assertEquals(endTime, roomBooking.getEndTime());
				assertEquals(date, roomBooking.getDate());
				
			}
			@Test
			public void testUpdateInvalidTimesRoomBooking() {

				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e){
					fail();
				}
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(1, allRoomBookings.size());
				assertEquals(id, allRoomBookings.get(0).getId());
				assertEquals(startTime, allRoomBookings.get(0).getStartTime());
				assertEquals(endTime, allRoomBookings.get(0).getEndTime());
				assertEquals(date, allRoomBookings.get(0).getDate());
				
				startTime = Time.valueOf("22:00:00");
				endTime = Time.valueOf("03:00:00");
				date = Date.valueOf("2019-10-20");
				
				try {
					service.updateRoomBooking(id, startTime, endTime, date);
					fail();
				}
				catch(IllegalArgumentException e) {
					assertEquals("Start time must be between 09:00 and 21:00. End time must be between 09:00 and 21:00. Start time must be before End time.", e.getMessage().trim());
				}
				
			}
			
			@Test
			public void testUpdateNullRoomBooking() {

				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e){
					fail();
				}
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(1, allRoomBookings.size());
				assertEquals(id, allRoomBookings.get(0).getId());
				assertEquals(startTime, allRoomBookings.get(0).getStartTime());
				assertEquals(endTime, allRoomBookings.get(0).getEndTime());
				assertEquals(date, allRoomBookings.get(0).getDate());
				
				startTime = null;
				endTime = null;
				date = null;
				id = null;
				
				try {
					service.updateRoomBooking(id, startTime, endTime, date);
					fail();
				}
				catch(IllegalArgumentException e) {
					assertEquals("ID is invalid. Start time is null. End time is null. Date is null.", e.getMessage().trim());
				}
				
			}
			
			@Test
			public void testCreateRoomBookingInvalidID() {
				
				Integer id = -1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("ID is invalid.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}
			
			@Test
			public void testCreateRoomBookingNullStartTime() {
				
				Integer id = 1;
				Time startTime = null;
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Start time is null.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
			}
			
			@Test
			public void testCreateRoomBookingNullEndTime() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = null;
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("End time is null.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}
			
			@Test
			public void testCreateRoomBookingNullDate() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = null;
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Date is null.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
			}
			
			@Test
			public void testCreateRoomBookingInvalidStartTime() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("23:00:00");
				Time endTime = Time.valueOf("17:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Start time must be between 09:00 and 21:00. Start time must be before End time.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}
			
			@Test
			public void testCreateRoomBookingInvalidEndTime() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("23:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("End time must be between 09:00 and 21:00.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}
			
			@Test
			public void testCreateRoomEndTimeBeforeStartTime() {
				
				Integer id = 1;
				Time startTime = Time.valueOf("16:00:00");
				Time endTime = Time.valueOf("15:00:00");
				Date date = Date.valueOf("2019-10-10");
				
				String error = null;
				
				try {
					service.createRoomBooking(id, startTime, endTime, date);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Start time must be before End time.", error.trim());
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}
			
			@Test
			public void testGetCourse() {
				University u = service.createUniversity(1, "McGill");
				service.createCourse("ECSE200", "Circuits 1", u);
				try {
					Course c = service.getCourse("ECSE200");
					assertEquals(c.getCourseCode(), service.getAllCourses().get(0).getCourseCode());
				}catch(IllegalArgumentException e){
					fail();
				}
			}
			
			@Test
			public void testGetNullCourse() {
				try {
					Course c = service.getCourse(null);
					fail();
				}catch(IllegalArgumentException e){
					assertEquals("Course can't be empty.", e.getMessage());
				}
			}
			
			@Test
			public void testGetNonCourse() {
				try {
					service.getCourse("SampleCourse");
					fail();
				}catch(IllegalArgumentException e){
					assertEquals("The course does not exist. Please specify a valid Course Code", e.getMessage());
				}
				
			}
			
//			@Test
//			public void testNotifyTutor() {
//				
//				Date date = Date.valueOf("2019-10-10");
//				Time startTime = Time.valueOf("10:00:00");
//				Time endTime = Time.valueOf("14:00:00");
//				
//				String name = "Joseph";
//				String username = "tutor1";
//				String password = "tutorPassword1";
//				double rate = 18;
//				
//				Tutor tutor = service.createTutor(name, username, password, rate);
//			
//				name = "richard";
//				username = "student1";
//				password = "studentPassword1";
//				
//				Student student = service.createStudent(username, password, name);
//				Set <Student> students = new HashSet<>();
//				students.add(student);
//				
//				int id = 4;
//				Boolean isConfirmed = false;
//		        Boolean isGroupSession = false;
//		        
//		        University university = service.createUniversity(1, "McGill University");
//		        Room room = service.createRoom(1, false);
//				Course course = service.createCourse("Math141", "Math", university);
//				
//		        Session s = service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, students, tutor, room, course);
//				
//				try {
//					service.notifyTutor(tutor, s);
//				} catch(IllegalArgumentException e) {
//					fail();
//				}
//				assertEquals(true,true);
//			}
			
			@Test
			public void testGetNullSession() {
				try {
					Integer id = null;
					service.getSession(id);
					fail();
				}catch(IllegalArgumentException e) {
					assertEquals("ID is invalid", e.getMessage());
				}			}
			
			@Test
			public void testGetInvalidSession() {
				try {
					service.getSession(-1);
					fail();
				}catch(IllegalArgumentException e) {
					assertEquals("ID is invalid", e.getMessage());
				}
			}
}
