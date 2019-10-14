package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomBookingRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.UniversityRepository;
//import ca.mcgill.ecse321.tutoringsystem.dao.UserRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Review;
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
	private ReviewRepository reviewRepository;
	
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
		reviewRepository.deleteAll();
		roomBookingRepository.deleteAll();
		roomRepository.deleteAll();
		courseRepository.deleteAll();
		universityRepository.deleteAll();
		studentRepository.deleteAll();
		tutorRepository.deleteAll();
		//userRepository.deleteAll();
		
	}
	/*
	 * //User Tests
	 * 
	 * @Test public void testCreateUser() {
	 * assertEquals(0,service.getAllStudents().size());
	 * assertEquals(0,service.getAllStudents().size());
	 * 
	 * String name = "User"; String username = "user123"; String password =
	 * "Userpassword1";
	 * 
	 * try { service.createUser(name, username, password);
	 * System.out.println("THE CREATE USER"); } catch (IllegalArgumentException e) {
	 * 
	 * fail(); }
	 * 
	 * List<User> allUsers = service.getAllUsers();
	 * 
	 * assertEquals(1, allUsers.size()); assertEquals(name,
	 * allUsers.get(0).getName()); assertEquals(username,
	 * allUsers.get(0).getUsername());
	 * assertEquals(password,allUsers.get(0).getPassword()); }
	 * 
	 * @Test public void testUpdateUser() {
	 * 
	 * String name = "User"; String username = "user123"; String password =
	 * "Userpassword1";
	 * 
	 * 
	 * try { service.createUser(name, username, password); } catch
	 * (IllegalArgumentException e) {
	 * 
	 * fail(); }
	 * 
	 * List<User> allUsers = service.getAllUsers();
	 * 
	 * assertEquals(1, allUsers.size());
	 * 
	 * name = "User"; password = "newPassword1"; String newUsername= "newuser123";
	 * 
	 * try { service.updateUser(name, username, newUsername, password); } catch
	 * (IllegalArgumentException e) {
	 * 
	 * fail(); }
	 * 
	 * assertEquals(1, allUsers.size()); assertEquals(name,
	 * allUsers.get(0).getName()); assertEquals(newUsername,
	 * allUsers.get(0).getUsername());
	 * assertEquals(password,allUsers.get(0).getPassword()); }
	 * 
	 * @Test public void deleteUser() {
	 * 
	 * String name = "User"; String username = "user123"; String password =
	 * "Userpassword1";
	 * 
	 * try { service.createUser(name, username, password); } catch
	 * (IllegalArgumentException e) {
	 * 
	 * fail(); }
	 * 
	 * List<User> allUsers = service.getAllUsers();
	 * 
	 * assertEquals(1, allUsers.size());
	 * 
	 * try { service.deleteUser(username); } catch (IllegalArgumentException e) {
	 * 
	 * fail(); } allUsers = service.getAllUsers();
	 * 
	 * assertEquals(0, allUsers.size()); }
	 */
	
	/*
	 * @Test public void testCreateUserNullName() {
	 * 
	 * String name = null; String username = "user123"; String password =
	 * "Userpassword1";
	 * 
	 * 
	 * String error = null; try { service.createUser(name, username, password); }
	 * catch (IllegalArgumentException e) {
	 * 
	 * error = e.getMessage(); }
	 * 
	 * assertEquals("Invalid name.", error); List<User> allUsers =
	 * service.getAllUsers(); assertEquals(0, allUsers.size());
	 * 
	 * }
	 * 
	 * @Test public void testCreateUserNullEmail() {
	 * 
	 * String name = "User"; String username = null; String password =
	 * "Userpassword1";
	 * 
	 * 
	 * 
	 * String error = null; try { service.createUser(name, username, password); }
	 * catch (IllegalArgumentException e) {
	 * 
	 * error = e.getMessage(); }
	 * 
	 * assertEquals("Invalid username.", error); List<User> allUsers =
	 * service.getAllUsers(); assertEquals(0, allUsers.size());
	 * 
	 * }
	 * 
	 * @Test public void testCreateUserNullPassword() {
	 * 
	 * String name = "User"; String username = "user123"; String password = null;
	 * 
	 * 
	 * 
	 * String error = null; try { service.createUser(name, username, password); }
	 * catch (IllegalArgumentException e) {
	 * 
	 * error = e.getMessage(); }
	 * 
	 * assertEquals("Invalid password.", error); List<User> allUsers =
	 * service.getAllUsers(); assertEquals(0, allUsers.size());
	 * 
	 * }
	 */
	
	//Student Tests
	
	@Test
	public void testCreateStudent() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";
		
		try {
			service.createStudent(username, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(username, allStudents.get(0).getUsername());
		assertEquals(password, allStudents.get(0).getPassword());
		assertEquals(schoolName, allStudents.get(0).getSchoolName());
	}
	
	@Test
	public void testUpdateStudent() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";

		try {
			service.createStudent(username, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		
		String newusername = "newStudent1";
		password = "newStudentPassword";
		schoolName = "Concordia";
		try {
			service.updateStudent(username, newusername, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		assertEquals(username, allStudents.get(0).getUsername());
		assertEquals(password, allStudents.get(0).getPassword());
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(schoolName, allStudents.get(0).getSchoolName());
	}
	
	
	@Test
	public void testDeleteStudent() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";

		try {
			service.createStudent(username, password, name, schoolName);
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
		String schoolName = "McGill";
		
		String error = null;

		try {
			service.createStudent(username, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Please enter a valid username.", error);
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullPassword() {

		String name = "richard";
		String username = "student1";
		String password = null;
		String schoolName = "McGill";
	
		String error = null;

		try {
			service.createStudent(username, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Please enter a valid password.", error);
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullSchoolName() {

		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = null;
		
		String error = null;

		try {
			service.createStudent(username, password, name, schoolName);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Please enter a valid school name.", error);
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
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


		try {
			service.createTutor(name, username, password,rate);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(1, allTutors.size());
		
		String newUsername = "tutor2";
		name = "Michael";
		password = "newTutorpass";
		rate  = 14;
		
		try {
			service.updateTutor(username, newUsername, name, password, rate);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		assertEquals(newUsername, allTutors.get(0).getUsername());
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(password, allTutors.get(0).getPassword());
		assertEquals(rate, allTutors.get(0).getHourlyRate(), 0.01);
		
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

		assertEquals("Please enter a valid name.", error);
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

		assertEquals("Please enter a valid username.", error);
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

		assertEquals("Please enter a valid password.", error);
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

		assertEquals("Please enter a valid hourlyRate.", error);
		List<Tutor> allTutors = service.getAllTutors();

		assertEquals(0, allTutors.size());
	}
	
	
	
	
	//University tests
	
	@Test
	public void testCreateUniversity() {

		String name= "McGill University";

		try {
			service.createUniversity(name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<University> allUniversities = service.getAllUniversities();

		assertEquals(1, allUniversities.size());
		assertEquals(name,allUniversities.get(0).getName());		
		}
	

	@Test
	public void testUpdateUniversity() {

		String name = "McGill University";
		
		try {
			service.createUniversity(name);
		} catch (IllegalArgumentException e) {
			
			fail();
		}

		List<University> allUniversities = service.getAllUniversities();

		assertEquals(1, allUniversities.size());
		
		String newName = "Concordia University";
	
		try {
			service.updateUniversity(name, newName);
		} catch (IllegalArgumentException e) {
			
			fail();
		}
		
		assertEquals(1, allUniversities.size());
		assertEquals(newName, allUniversities.get(0).getName());
		
	}
	
	@Test
	public void testCreateUniversityNullName() {
		String name= null;
		String error = null;
		try {
			service.createUniversity(name);
		} catch (IllegalArgumentException e) { 
			error = e.getMessage();
		}

		assertEquals("Invalid name.", error);
		List<University> allUniversities = service.getAllUniversities();
		assertEquals(0, allUniversities.size());
		
		}
	
	
	// <----StudentReview---->
	
	@Test
	public void testCreateStudentReview() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";
		
		Student reviewee = service.createStudent(username, password, name, schoolName);
		int id = 1;
		String comments = "Good Student, listens well!";
		
		try {
			service.createStudentReview(id,comments,reviewee);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals(1, allReviews.size());
		assertEquals(id, allReviews.get(0).getId(), 0);
		assertEquals(comments, allReviews.get(0).getReview());
		assertEquals(username, allReviews.get(0).getStudentReviewee().getUsername());
	}
	
	@Test
	public void testUpdateStudentReview() {
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";
		
		Student reviewee = service.createStudent(username, password, name, schoolName);
		int id = 1;
		String comments = "Good Student, listens well!";
		

		try {
			service.createStudentReview(id,comments,reviewee);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals(1, allReviews.size());
		
		int newId = 2;
		comments = "Pain in the ass";
		
		try {
			service.updateStudentReview(id, newId, comments, reviewee);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		
	}
	
	@Test
	public void testCreateStudentReviewNullReviewee() {

		Student reviewee = null;
		int id = 1;
		String comments = "Good Student, listens well!";
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("Please insert a reviewee.", error);

		List<Review> allReviews = service.getAllReviews();

		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateStudentReviewNullComments() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";
		
		Student reviewee = service.createStudent(username, password, name, schoolName);
		int id = 1;
		String comments = null;
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("Please insert a comment.", error);

		List<Review> allReviews = service.getAllReviews();

		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateStudentReviewInvalidId() {
		
		String name = "richard";
		String username = "student1";
		String password = "studentPassword1";
		String schoolName = "McGill";
		
		Student reviewee = service.createStudent(username, password, name, schoolName);
		int id = -1;
		String comments = "good student";
		
		String error = null;

		try {
			service.createStudentReview(id,comments, reviewee);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		// check error
		assertEquals("Incorrect id value.", error);

		List<Review> allReviews = service.getAllReviews();

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
		int id = 1;
		String comments = "Good Student, listens well!";
		int rating = 4;
		try {
			service.createTutorReview(id,comments,reviewee, rating);
		} catch (IllegalArgumentException e) { 
			fail();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals(1, allReviews.size());
		assertEquals(id, allReviews.get(0).getId(), 0);
		assertEquals(comments, allReviews.get(0).getReview());
		assertEquals(username, allReviews.get(0).getTutorReviewee().getUsername());
	}
	@Test
	public void testCreateTutorReviewNullReviewee() {
		
		Tutor reviewee = null;
		int id = 1;
		String comments = "Good Student, listens well!";
		int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals("Please insert a reviewee.", error);


		assertEquals(0, allReviews.size());
	}
	
	
	@Test
	public void testCreateTutorReviewNullComments() {
		
	
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
	int id = 1;
	String comments = null;
	int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals("Please insert a comment.", error);


		assertEquals(0, allReviews.size());
	}
	

	@Test
	public void testCreateTutorReviewInvalidId() {
		
	
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
    int id = -1;
	String comments = "Good Student, listens well!";
	int rating = 4;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals("Incorrect id value.", error);


		assertEquals(0, allReviews.size());
	}
	
	@Test
	public void testCreateTutorReviewInvalidRating() {
		
	String name = "Joseph";
	String username = "tutor1";
	String password = "tutorPassword1";
	double rate = 18;
	
	Tutor reviewee = service.createTutor(name, username, password, rate);
    int id = 1;
	String comments = "Good Student, listens well!";
	int rating = 10;
		
		String error = null;
		try {
			service.createTutorReview(id,comments,reviewee, rating);
		} catch (IllegalArgumentException e) { 
			error=e.getMessage();
		}
		
		List<Review> allReviews = service.getAllReviews();
        
		assertEquals("Please insert a valid rating.", error);


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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
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
		assertEquals(course, allSessions.get(0).getCourse());
		assertEquals(room, allSessions.get(0).getRoom());
		assertEquals(student, allSessions.get(0).getStudent());
		assertEquals(tutor, allSessions.get(0).getTutor());
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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Invalid date or time parameters.",error);


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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Invalid date or time parameters.",error);

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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Invalid date or time parameters.",error);

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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
       
        Room room = service.createRoom(1, false);
		Course course = null;
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("course can't be null.",error);

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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = null;
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("room can't be null.",error);

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
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Please enter valid tutor and student.",error);

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
		String schoolName = "McGill";
		
		Student student = service.createStudent(username, password, name, schoolName);
		
		int id = 4;
		Boolean isConfirmed = false;
        Boolean isGroupSession = false;
        
        University university = service.createUniversity("McGill University");
        Room room = service.createRoom(1, false);
		Course course = service.createCourse("Math141", "Math", university);
        
		String error = null;
		try {
			service.createSession(id,isConfirmed,startTime,endTime,date,isGroupSession, student, tutor, room, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		

		List<Session> allSessions = service.getAllSessions();
		
		assertEquals("Please enter valid tutor and student.",error);

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
		public void testUpdateRoom() {
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
			
			int newRoomNumber  = 2;
			Boolean newIsLargeRoom = false; //MAY CAUSE ISSUES
			
			try {
				service.updateRoom(roomNumber, newRoomNumber, newIsLargeRoom);
			} catch (IllegalArgumentException e) {
				fail();
			}

			allRooms = service.getAllRooms();
			assertEquals(1, allRooms.size());
			assertEquals(newRoomNumber, (int) allRooms.get(0).getRoomNr());
			assertEquals(newIsLargeRoom, allRooms.get(0).getIsLargeRoom());
			
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

			assertEquals("Room number cannot be negative", error);
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

			assertEquals("Room Type (isLarge true or false) cannot be null", error);
			List<Room> allRooms = service.getAllRooms();

			assertEquals(0, allRooms.size());

		}
		

		// -----------------Course Class Tests------------------
			
			@Test
			public void testCreateCourse() {
				
				//CHECK IF THIS WORKS??? IF THIS METHOD CREATES ISSUES
				
				
				String courseCode = "ECSE321";
				String subject = "SoftwareEng";
				University university =  service.createUniversity("McGill");
				
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
				assertEquals(university, allCourses.get(0).getUniversity());
					
			}
			
			@Test
			public void testUpdateCourse() {
				
				String courseCode = "ECSE321";
				String subject = "SoftwareEng";
				University university =  service.createUniversity("McGill");
				
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
				assertEquals(university, allCourses.get(0).getUniversity());
				
				String newCourseCode = "MATH140";
				String newSubject = "Science";
				University newUniversity = service.createUniversity("Concordia");
				
				try {
					service.updateCourse(courseCode, newSubject, newCourseCode, newUniversity);
				} catch (IllegalArgumentException e) {
					fail();
				}

				assertEquals(1, allCourses.size());
				assertEquals(newCourseCode, allCourses.get(0).getCourseCode());	
				assertEquals(newSubject, allCourses.get(0).getSubject());
				assertEquals(newUniversity, allCourses.get(0).getUniversity());
				
			}
			
			//DELETE COURSE METHOD NEEDS TO BE IMPLEMENTED
			
			@Test
			public void testCreateCourseNullCourseCode() {
				
				String courseCode = null;
				String subject = "Science";
				University university = service.createUniversity("McGill");
				
				String error = null;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Please provide a courseCode", error);
				List<Course> allCourses = service.getAllCourses();
				assertEquals(0, allCourses.size());
				
				
			}
			
			@Test
			public void testCreateCourseNullSubject() {
				
				String courseCode = "ECSE321";
				String subject = null;
				University university = service.createUniversity("McGill");
				
				String error = null;
				
				try {
					service.createCourse(courseCode, subject, university);
				}
				catch (IllegalArgumentException e) {
					error = e.getMessage();
				}
				
				assertEquals("Please provide a subject", error);
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
				
				assertEquals("University cannot be null", error);
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
				
				id = 2;
				startTime = Time.valueOf("15:00:00");
				endTime = Time.valueOf("16:00:00");
				date = Date.valueOf("2019-10-20");
				
				try {
					service.updateRoomBooking(id, startTime, endTime, date);
				}
				catch(IllegalArgumentException e) {
					fail();	
				}
				
				assertEquals(1, allRoomBookings.size());
				assertEquals(id, allRoomBookings.get(0).getId());
				assertEquals(startTime, allRoomBookings.get(0).getStartTime());
				assertEquals(endTime, allRoomBookings.get(0).getEndTime());
				assertEquals(date, allRoomBookings.get(0).getDate());
				
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
				
				assertEquals("Incorrect value for ID. Note: ID must be positive.", error);
				
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
				
				assertEquals("Your startTime cannot be null. Incorrect Start Time", error);
				
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
				
				assertEquals("Your endTime cannot be null. Incorrect End Time", error);
				
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
				
				assertEquals("Your date cannot be null. Incorrect date", error);
				
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
				
				assertEquals("Start time must be between 09:00 and 21:00", error);
				
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
				
				assertEquals("End time must be between 09:00 and 21:00", error);
				
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
				
				assertEquals("Start time must be before End time", error);
				
				List <RoomBooking> allRoomBookings = service.getAllRoomBookings();
				
				assertEquals(0, allRoomBookings.size());
				
				
			}

}