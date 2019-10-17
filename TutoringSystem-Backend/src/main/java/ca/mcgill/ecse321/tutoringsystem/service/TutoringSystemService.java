package ca.mcgill.ecse321.tutoringsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

@Service
public class TutoringSystemService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	RoomBookingRepository roomBookingRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	UniversityRepository universityRepository;
	
	/*
	 * //@Autowired //UserRepository userRepository;
	 */	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	@Transactional
	public Course createCourse(String courseCode, String subject, University university) {
		String error = "";
		if(courseCode == null || courseCode.trim().length() == 0){
			error +="Please provide a courseCode. ";
		}
		if(subject == null || subject.trim().length() == 0){
			error +="Please provide a subject. ";
		}
		if(university == null){
			error +="University cannot be null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Course c = new Course();
		c.setCourseCode(courseCode);
		c.setSubject(subject);
		c.setUniversity(university);
		courseRepository.save(c);
		return c;
	}
	
	@Transactional
	public Course updateCourse(String oldCourseCode, String subject, String courseCode, University university) {
		String error = "";
		if(oldCourseCode == null || oldCourseCode.trim().length() == 0){
			error +="Please provide an oldCourseCode. ";
		}
		if(courseCode == null || courseCode.trim().length() == 0){
			error +="Please provide a courseCode. ";
		}
		if(subject == null || subject.trim().length() == 0){
			error +="Please provide a subject. ";
		}
		if(university == null){
			error +="University cannot be null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Course course = courseRepository.findByCourseCode(oldCourseCode);
		if( course == null)
			throw new IllegalArgumentException("The course does not exist. Please specify a valid Course Code");

		course.setSubject(subject);
		course.setCourseCode(courseCode);
		course.setUniversity(university);
		course.setUniversity(university);
		courseRepository.save(course);
		return course;
	}
	
	@Transactional
	public Course getCourse(String courseCode) {
		if(courseCode == null || courseCode.trim().length() == 0){
			throw new IllegalArgumentException("Course can't be empty");
		}
		Course c = courseRepository.findByCourseCode(courseCode);
		return c;
	}
	
	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}
	
	@Transactional
	public Review createStudentReview(Integer id, String comments, Student reviewee) {
		String error = "";
		if(id < 0){
			error +="ID is invalid. ";
		}
		if(comments == null || comments.trim().length() == 0){
			error +="Comments can't be empty. ";
		}
		if(reviewee == null){
			error +="Reviewee is null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		StudentReview sr = new StudentReview();
		sr.setId(id);
		sr.setReview(comments);
		sr.setStudentReviewee(reviewee);
		reviewRepository.save(sr);
		return sr;
	}
	
	@Transactional
	public Review updateStudentReview(int oldID, int id, String comments, Student reviewee) {
		String error = "";
		if(id < 0 ||  oldID < 0){
			error += "Incorrect id value. ";
		}
		if(reviewee == null){
			error += "Please insert a reviewee";
		}
		if(comments == null || comments.equals("")){
			error += "Please insert a comment. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Review review = reviewRepository.findById(oldID).get();
		if(review == null)
			throw new IllegalArgumentException("Please enter a valid Review to update.");
		
		review.setId(id);
		review.setReview(comments);
		review.setStudentReviewee(reviewee);
		
		reviewRepository.save(review);
		return review;
	}
	@Transactional
	public Review createTutorReview(Integer id, String comments, Tutor reviewee, Integer rating) {
		String error = "";		
		if(id < 0){
			error +="ID is invalid. ";
		}
		if(comments == null || comments.trim().length() == 0){
			error +="Comments can't be empty. ";
		}
		if(reviewee == null){
			error +="Reviewee is null. ";
		}
		if(rating < 0 || rating > 5){
			error +="Rating is invalid. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		TutorReview tr = new TutorReview();
		tr.setId(id);
		tr.setReview(comments);
		tr.setTutorReviewee(reviewee);
		tr.setRating(rating);
		reviewRepository.save(tr);
		return tr;
	}

	@Transactional
	public Review getReview(Integer id) {
		if(id < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		Review r = reviewRepository.findById(id).get();
		return r;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public Room createRoom(Integer roomNr, Boolean isLargeRoom) {
		String error = "";
		if(roomNr < 0){
			error +="Room number is invalid. ";
		}
		if(isLargeRoom == null){
			error +="Room Type (isLarge = true || false) cannot be null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Room r = new Room();
		r.setRoomNr(roomNr);
		r.setIsLargeRoom(isLargeRoom);
		roomRepository.save(r);
		return r;
	}
	
	@Transactional
	public Room getRoom(Integer roomNr) {
		if(roomNr < 0){
			throw new IllegalArgumentException("Room number is invalid");
		}
		Room r = roomRepository.findById(roomNr).get();
		return r;
	}
	
	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}
	
	@Transactional
	public boolean deleteRoom(int roomNr) {
		if(roomNr < 0){
			throw new IllegalArgumentException("Room number is invalid");
		}

		boolean done = false;
		Room a = getRoom(roomNr);

		if (a != null) {
			roomRepository.delete(a);
			done = true;
		}
		return done;
	}
	
	@Transactional
	public Room updateRoom(int oldRoomNumber, int roomNumber, Boolean isLarge) {
		String error = "";
		if(roomNumber < 0){
			error +="Room number is invalid. ";
		}
		if(isLarge == null){
			error +="Room Type (isLarge = true || false) cannot be null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		Room room = roomRepository.findById(oldRoomNumber).get();
		Room rm = roomRepository.findById(roomNumber).get();
		if(rm != null){
			throw new IllegalArgumentException("Room already exists with that ID.");
		}else{
			room.setRoomNr(roomNumber);
			room.setIsLargeRoom(isLarge);
			roomRepository.save(room);
		}
		return room;
	}
	
	@Transactional
	public RoomBooking createRoomBooking(Integer id, Time startTime, Time endTime, Date date) {
		String error = "";
		if(id < 0){
			error +="ID is invalid. ";
		}
		if(startTime == null){
			error +="Start time is null. ";
		}
		if(endTime == null ){
			error +="End time is null. ";
		}
		if(date == null){
			error +="Date is null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		if(startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))) {
			error += "Start time must be between 09:00 and 21:00. ";
		}
		if(endTime.before(Time.valueOf("09:00:00")) || endTime.after(Time.valueOf("21:00:00"))) {
			error += "End time must be between 09:00 and 21:00. ";
		}
		if(endTime.before(startTime)) {
			error += "Start time must be before End time. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}		
		
		RoomBooking rb = new RoomBooking();
		rb.setId(id);
		rb.setStartTime(startTime);
		rb.setEndTime(endTime);
		rb.setDate(date);
		roomBookingRepository.save(rb);
		return rb;
	}
	
	@Transactional
	public RoomBooking updateRoomBooking (Integer id, Time startTime, Time endTime, Date date) {
		String error = "";
		if(id < 0){
			error +="ID is invalid. ";
		}
		if(startTime == null){
			error +="Start time is null. ";
		}
		if(endTime == null ){
			error +="End time is null. ";
		}
		if(date == null){
			error +="Date is null. ";
		}
		if(startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))) {
			error += "Start time must be between 09:00 and 21:00";
		}
		if(endTime.before(Time.valueOf("09:00:00")) || endTime.after(Time.valueOf("21:00:00"))) {
			error += "End time must be between 09:00 and 21:00";
		}
		if(endTime.before(startTime)) {
			error += "Start time must be before End time";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}		
		
		RoomBooking roomBooking = roomBookingRepository.findById(id).get();
		roomBooking.setStartTime(startTime);
		roomBooking.setEndTime(endTime);
		roomBooking.setDate(date);
		roomBookingRepository.save(roomBooking);
		
		return roomBooking;

	}
	
	@Transactional
	public RoomBooking getRoomBooking(Integer id) {
		RoomBooking rb = roomBookingRepository.findById(id).get();
		return rb;
	}
	
	@Transactional
	public List<RoomBooking> getAllRoomBookings(){
		return toList(roomBookingRepository.findAll());
	}
	
	@Transactional
	public Session createSession(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Student tutee, Tutor tutor, Room room, Course course) {
		
		String error = "";
		if(room == null){
			error += "Room number is invalid. ";
		}
		if(isConfirmed == null){
			error += "Is Confirmed is null. ";
		}
		if(startTime == null || startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))){
			error +="Start time is invalid. ";
		}
		if(endTime == null || endTime.after(Time.valueOf("21:00:00")) || endTime.before(startTime)){
			error +="End time is invalid. ";
		}
		if(date == null){
			error +="Date is null. ";
		}
		if(isGroupSession == null){
			error +="Is group session is null. ";
		}
		if(tutee == null){
			error +="Tutee is null. ";
		}
		if(tutor == null){
			error +="Tutor is null. ";
		}
		if(room == null){
			error +="Room is null. ";
		}
		if(course == null){
			error +="Course is null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Session s = new Session();
		s.setId(id);
		s.setIsConfirmed(isConfirmed);
		s.setStartTime(startTime);
		s.setEndTime(endTime);
		s.setDate(date);
		s.setIsGroupSession(isGroupSession);
		s.getStudent().add(tutee);
		s.setTutor(tutor);
		s.setRoom(room);
		s.setCourse(course);
		sessionRepository.save(s);
		return s;
	}
	
	@Transactional
	public Session getSession(Integer id) {
		if(id < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		Session s = sessionRepository.findById(id).get();
		return s;
	}
	
	@Transactional
	public List<Session> getAllSessions() {
		return toList(sessionRepository.findAll());
	}
	
	@Transactional
	public Student createStudent(String username, String password, String name, String schoolName) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(name == null || name.trim().length() == 0){
			error +="Name can't be empty. ";
		}
		if(schoolName == null || schoolName.trim().length() == 0){
			error +="School name can't be empty. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		
		Student s = new Student();
		s.setUsername(username);
		s.setPassword(password);
		s.setName(name);
		s.setSchoolName(schoolName);
		studentRepository.save(s);
		return s;
	}
	
	@Transactional
	public Student updateStudent(String username, String oldName, String password, String newName, String schoolName) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(newName == null || newName.trim().length() == 0){
			error +="New name can't be empty. ";
		}
		if(oldName == null || oldName.trim().length() == 0){
			error +="Old name can't be empty. ";
		}
		if(schoolName == null || schoolName.trim().length() == 0){
			error +="School name can't be empty. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Student student = studentRepository.findStudentByUsername(username);
		if(student == null)
			throw new IllegalArgumentException("Please input a valid student");
		student.setUsername(username);
		student.setPassword(password);
	    student.setSchoolName(schoolName);
		studentRepository.save(student);
		return student;
	}
	
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}
	
	@Transactional
	public Student getStudent(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		Student s = studentRepository.findStudentByUsername(username);
		return s;
	}
	
	@Transactional
	public boolean deleteStudent(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		boolean done = false;
		Student s = getStudent(username);
		if (s != null) {
			studentRepository.delete(s);
			done = true;
		}
		return done;
	}

	@Transactional
	public Tutor createTutor(String username, String password, String name, Double hourlyRate) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(name == null || name.trim().length() == 0){
			error +="Name can't be empty. ";
		}
		if(hourlyRate < 0){
			error += "Hourly rate is invalid";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Tutor t = new Tutor();
		t.setUsername(username);
		t.setPassword(password);
		t.setName(name);
		t.setHourlyRate(hourlyRate);
		tutorRepository.save(t);
		return t;
	}

	//TODO: What is this all commented out for. Also why are there 2 create tutors
	
	/*
	 * @Transactional public User createUser(String name, String username, String
	 * password) {
	 * 
	 * if(name == null || name.equals("")){ throw new
	 * IllegalArgumentException("Invalid name."); } if(username == null ||
	 * username.equals("")){ throw new
	 * IllegalArgumentException("Invalid username."); } if(password == null ||
	 * password.equals("")){ throw new
	 * IllegalArgumentException("Invalid password."); }
	 * 
	 * 
	 * User user = new User(); user.setName(name); user.setUsername(username);
	 * user.setPassword(password); userRepository.save(user); return user; }
	 */

	/*
		 * @Transactional public User updateUser(String name, String oldUsername,String
		 * newUsername, String password) { if(name == null || name.equals("")){ throw
		 * new IllegalArgumentException("Invalid name."); } if(oldUsername == null ||
		 * oldUsername.equals("")){ throw new
		 * IllegalArgumentException("Invalid username."); } if(newUsername == null ||
		 * newUsername.equals("")){ throw new
		 * IllegalArgumentException("Invalid username."); } if(password == null ||
		 * password.equals("")){ throw new
		 * IllegalArgumentException("Invalid password."); } User user =
		 * userRepository.findById(oldUsername).get(); user.setName(name);
		 * user.setUsername(newUsername); user.setPassword(password);
		 * userRepository.save(user); return user; }
		 * 
		 * @Transactional public User getUser(String username) { User u =
		 * userRepository.findUserByUserName(username); return u; }
		 */
	
	/*@Transactional
	public List<User> getAllUsers() {
		return toList(userRepository.findAll());
	}*/
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
	/*
	 * @Transactional public boolean deleteUser(String username) { boolean done =
	 * false; User u = getUser(username); if (u != null) { userRepository.delete(u);
	 * done = true; } return done; }
	 */
	
	
	public Tutor createTutor(String name, String username, String password, double hourlyRate) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(name == null || name.trim().length() == 0){
			error +="Name can't be empty. ";
		}
		if(hourlyRate < 0){
			error += "Hourly rate is invalid";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		Tutor tutor = new Tutor();
		tutor.setName(name);
		tutor.setUsername(username);
		tutor.setPassword(password);
		tutor.setHourlyRate(hourlyRate);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	@Transactional
	public Tutor getTutor(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		Tutor a = tutorRepository.findTutorByUsername(username);
		return a;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}
	
	@Transactional
	public Tutor updateTutor(String username, String oldName, String newName, String password, double hourlyRate) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(newName == null || newName.trim().length() == 0){
			error +="New name can't be empty. ";
		}
		if(oldName == null || oldName.trim().length() == 0){
			error +="Old name can't be empty. ";
		}
		if(hourlyRate < 0) {
			error +="Hourly rate is invalid. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Tutor tutor = tutorRepository.findTutorByUsername(oldName);
		if(tutor == null) {
			throw new IllegalArgumentException("Please enter a valid tutor to update");
			}
		tutor.setUsername(username);
		tutor.setName(newName);
		tutor.setPassword(password);
		tutor.setHourlyRate(hourlyRate);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	@Transactional
	public boolean deleteTutor(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		boolean done = false;
		Tutor t = getTutor(username);
		if (t != null) {
			tutorRepository.delete(t);
			done = true;
		}
		return done;
	}
	
	@Transactional
	public University createUniversity (String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty. ");
		}
		University u = new University();
		u.setName(name);
		universityRepository.save(u);
		return u;
	}
	
	@Transactional
	public University getUniversity(String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty. ");
		}
		University u = universityRepository.findById(name).get();
		return u;
	}
	
	@Transactional
	public List<University> getAllUniversities() {
		return toList(universityRepository.findAll());
	}
	
	
	@Transactional
	public University updateUniversity(String name, String newName) {
		String error = "";
		if(name == null || name.trim().length() == 0){
			error += "Name can't be empty. ";
		}		
		if(newName == null || newName.trim().length() == 0){
			throw new IllegalArgumentException("New name can't be empty. ");
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		
		University university = universityRepository.findById(name).get();
		university.setName(newName);
		universityRepository.save(university);
		return university;
	}
}
