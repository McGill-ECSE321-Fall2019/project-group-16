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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	@Transactional
	public Course createCourse(String courseCode, String subject, University university) {
		Course c = new Course();
		c.setCourseCode(courseCode);
		c.setSubject(subject);
		c.setUniversity(university);
		courseRepository.save(c);
		return c;
	}
	
	@Transactional
	public Course getCourse(String courseCode) {
		Course c = courseRepository.findByCourseCode(courseCode);
		return c;
	}
	
	@Transactional
	public Review createStudentReview(Integer id, String comments, Student reviewee) {
		if(id < 0 ){
			throw new IllegalArgumentException("Incorrect id value.");
		}
		if(reviewee == null){
			throw new IllegalArgumentException("Please insert a reviewee.");
		}
		if(comments == null || comments.equals("")){
			throw new IllegalArgumentException("Please insert a comment.");
		}
		StudentReview sr = new StudentReview();
		sr.setId(id);
		sr.setReview(comments);
		sr.setReviewee(reviewee);
		reviewRepository.save(sr);
		return sr;
	}
	
	@Transactional
	public Review updateStudentReview(int oldID, int id, String comments, Student reviewee) {
		if(id < 0 ||  oldID < 0){
			throw new IllegalArgumentException("Incorrect id value.");
		}
		if(reviewee == null){
			throw new IllegalArgumentException("Please insert a reviewee.");
		}
		if(comments == null || comments.equals("")){
			throw new IllegalArgumentException("Please insert a comment.");
		}
		
		Review review = reviewRepository.findReviewByReviewID(oldID);
		if(review == null)
			throw new IllegalArgumentException("Please enter a valid Review to update.");
		
		review.setId(id);
		review.setReview(comments);
		review.setReviewee(reviewee);
		
		reviewRepository.save(review);
		return review;
	}
	@Transactional
	public Review createTutorReview(Integer id, String comments, Tutor reviewee, Integer rating) {
		if(id < 0 ){
			throw new IllegalArgumentException("Incorrect id value.");
		}
		if(reviewee == null){
			throw new IllegalArgumentException("Please insert a reviewee.");
		}
		if(comments == null || comments.equals("")){
			throw new IllegalArgumentException("Please insert a comment.");
		}if(rating < 0 || rating > 5) {
			throw new IllegalArgumentException("Please insert a valid rating.");
		}
		TutorReview tr = new TutorReview();
		tr.setId(id);
		tr.setReview(comments);
		tr.setReviewee(reviewee);
		tr.setRating(rating);
		reviewRepository.save(tr);
		return tr;
	}

	@Transactional
	public Review getReview(Integer id) {
		Review r = reviewRepository.findById(id).get();
		return r;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public Room createRoom(Integer roomNr, Boolean isLargeRoom) {
		Room r = new Room();
		r.setRoomNr(roomNr);
		r.setIsLargeRoom(isLargeRoom);
		roomRepository.save(r);
		return r;
	}
	
	@Transactional
	public Room getRoom(Integer roomNr) {
		Room r = roomRepository.findById(roomNr).get();
		return r;
	}
	
	@Transactional
	public RoomBooking createRoomBooking(Integer id, Time startTime, Time endTime, Date date) {
		RoomBooking rb = new RoomBooking();
		rb.setId(id);
		rb.setStartTime(startTime);
		rb.setEndTime(endTime);
		rb.setDate(date);
		roomBookingRepository.save(rb);
		return rb;
	}
	
	@Transactional
	public RoomBooking getRoomBooking(Integer id) {
		RoomBooking rb = roomBookingRepository.findById(id).get();
		return rb;
	}
	
	@Transactional
	public Session createSession(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Student tutee, Tutor tutor, Room room, Course course) {
	
		if(id<0) {
			throw new IllegalArgumentException("Incorrect id value.");
		}
		if(tutor == null || tutee == null ) {
			throw new IllegalArgumentException("Please enter valid tutor and student.");
		}
		if(date == null || startTime == null || endTime == null){
			throw new IllegalArgumentException("Invalid date or time parameters.");
		}
		if(room == null) {
			throw new IllegalArgumentException("room can't be null.");
		}
		if(course == null) {
			throw new IllegalArgumentException("course can't be null.");
		}
		if(startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))) {
			throw new IllegalArgumentException("startTime must be valid");
		}
		if(endTime.after(Time.valueOf("21:00:00")) || endTime.before(startTime)) {
			throw new IllegalArgumentException("endTime must be valid");
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
		Session s = sessionRepository.findById(id).get();
		return s;
	}
	
	@Transactional
	public List<Session> getAllSessions() {
		return toList(sessionRepository.findAll());
	}
	
	@Transactional
	public Student createStudent(String username, String password, String name, String schoolName) {
		
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(password.equals("") || password == null){
			throw new IllegalArgumentException("Please enter a valid password.");
		}
		if(schoolName.equals("") || schoolName == null) {
			throw new IllegalArgumentException("Please enter a valid school name.");
		}
		
		Student s = new Student();
		s.setUsername(username);
		s.setPassword(password);
		s.setName(name);
		s.setSchoolName(schoolName);
		userRepository.save(s);
		return s;
	}
	
	@Transactional
	public Student updateStudent(String oldUsername, String username, String password, String name, String schoolName) {
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(oldUsername.equals("") || oldUsername == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(password.equals("") || password == null){
			throw new IllegalArgumentException("Please enter a valid password.");
		}
		if(schoolName.equals("") || schoolName == null) {
			throw new IllegalArgumentException("Please enter a valid school name.");
		}
		Student student = studentRepository.findStudentByUsername(oldUsername);
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
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please insert a username...");
		}
		Student s = studentRepository.findStudentByUsername(username);
		return s;
	}
	
	@Transactional
	public boolean deleteStudent(String username) {
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please insert a username...");
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
		Tutor t = new Tutor();
		t.setUsername(username);
		t.setPassword(password);
		t.setName(name);
		t.setHourlyRate(hourlyRate);
		userRepository.save(t);
		return t;
	}
	@Transactional
	public User createUser(String name, String username, String password) {
		
		if(name == null || name.equals("")){
			throw new IllegalArgumentException("Invalid name.");
		}
		if(username == null || username.equals("")){
			throw new IllegalArgumentException("Invalid username.");
		}
		if(password == null || password.equals("")){
			throw new IllegalArgumentException("Invalid password.");
		}
		

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@Transactional
	public User updateUser(String name, String oldUsername,String newUsername, String password) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException("Invalid name.");
		}
		if(oldUsername == null || oldUsername.equals("")){
			throw new IllegalArgumentException("Invalid username.");
		}
		if(newUsername == null || newUsername.equals("")){
			throw new IllegalArgumentException("Invalid username.");
		}
		if(password == null || password.equals("")){
			throw new IllegalArgumentException("Invalid password.");
		}
		User user = userRepository.findById(oldUsername).get();
		user.setName(name);
		user.setUsername(newUsername);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}
	
	@Transactional
	public User getUser(String username) {
		User u = userRepository.findUserByUserName(username);
		return u;
	}
	
	@Transactional
	public List<User> getAllUsers() {
		return toList(userRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
	@Transactional
	public boolean deleteUser(String username) {
		boolean done = false;
		User u = getUser(username);
		if (u != null) {
			userRepository.delete(u);
			done = true;
		}
		return done;
	}
	
	
	public Tutor createTutor(String name, String username, String password, double hourlyRate) {
		if(name.equals("") || name == null){
			throw new IllegalArgumentException("Please enter a valid name.");
		}
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(password.equals("") || password == null){
			throw new IllegalArgumentException("Please enter a valid password.");
		}
		if(hourlyRate < 0){
			throw new IllegalArgumentException("Please enter a valid hourlyRate.");
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
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please insert a username...");
		}
		Tutor a = tutorRepository.findTutorByUsername(username);
		return a;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}
	
	@Transactional
	public Tutor updateTutor(String oldUsername, String username, String name, String password, double hourlyRate) {
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(oldUsername.equals("") || oldUsername == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(name.equals("") || name == null){
			throw new IllegalArgumentException("Please enter a valid username.");
		}
		if(password.equals("") || password == null){
			throw new IllegalArgumentException("Please enter a valid password.");
		}
		if(hourlyRate < 0){
			throw new IllegalArgumentException("Please enter a valid hourlyRate.");
		}
		
		Tutor tutor = tutorRepository.findTutorByUsername(oldUsername);
		if(tutor == null) {
			throw new IllegalArgumentException("Please enter a valid tutor to update");
			}
		tutor.setUsername(username);
		tutor.setName(name);
		tutor.setPassword(password);
		tutor.setHourlyRate(hourlyRate);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	@Transactional
	public boolean deleteTutor(String username) {
		if(username.equals("") || username == null){
			throw new IllegalArgumentException("Please enter a username.");
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
	public University createUniversity(String name) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException("Invalid name.");
		}
		University u = new University();
		u.setName(name);
		universityRepository.save(u);
		return u;
	}
	
	@Transactional
	public University getUniversity(String name) {
		University u = universityRepository.findById(name).get();
		return u;
	}
	
	@Transactional
	public List<University> getAllUniversities() {
		return toList(universityRepository.findAll());
	}
	
	@Transactional
	public University updateUniversity(String name, String newName) {
		if(name == null || name.equals("")){
			throw new IllegalArgumentException("Please enter a valid university name.");
		}
		if(newName == null || newName.equals("")){
			throw new IllegalArgumentException("Please enter a valid university name.");
		}
		
		University university = universityRepository.findUniversityByUniversityName(name);
		university.setName(newName);
		universityRepository.save(university);
		return university;
	}
	

}
