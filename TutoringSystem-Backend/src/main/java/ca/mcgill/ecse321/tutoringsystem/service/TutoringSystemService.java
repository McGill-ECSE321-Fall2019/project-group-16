package ca.mcgill.ecse321.tutoringsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@Service
public class TutoringSystemService {

	@Autowired
	CourseRepository courseRepository;
	
//	@Autowired
//	ReviewRepository reviewRepository;
	
	@Autowired
	StudentReviewRepository studentReviewRepository;
	
	@Autowired
	TutorReviewRepository tutorReviewRepository;
	
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
	
	/**
	 * This is a method to create a new course
	 * @param courseCode the couse code
	 * @param subject the subject that the course belongs to
	 * @param university the univerty that the course belongs to
	 * @return a course if we all the inputs are correct
	 */
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
	
	/**
	 * This is a method to update the status of a course
	 * @param courseCode the couse code
	 * @param subject the subject that the course belongs to
	 * @param university the univerty that the course belongs to
	 * @param isRequested a boolean that denotes if the course is being requested
	 * @param tutors a tutor set that store the tutors who are teaching this course
	 * @return a course if we all the inputs are correct
	 */
	@Transactional
	public Course updateCourse(String courseCode, String subject, University university, Boolean isRequested, Set<Tutor> tutors) {
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
		if(isRequested == null) {
			error +="Must be requested or not. ";
		}
		//ERROR CHECKING FOR TUTORS
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Course course = courseRepository.findByCourseCode(courseCode);
		if( course == null)
			throw new IllegalArgumentException("The course does not exist. Please specify a valid Course Code");

		course.setSubject(subject);
		course.setUniversity(university);
		course.setIsRequested(isRequested);
		course.setTutor(tutors);

		courseRepository.save(course);
		return course;
	}
	/**
	 * This is a method get the target with course code
	 * @param courseCode the course code
	 * @return a course with the input course code if it exits
	 */
	@Transactional
	public Course getCourse(String courseCode) {
		if(courseCode == null || courseCode.trim().length() == 0){
			throw new IllegalArgumentException("Course can't be empty.");
		}
		Course course = courseRepository.findByCourseCode(courseCode);
		if( course == null)
			throw new IllegalArgumentException("The course does not exist. Please specify a valid Course Code");

		return course;
	}
	/**
	 * This is a method to get all the courses in the backend 
	 * @return a list of courses 
	 */
	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}
	/**
	 * This is a method to create a new student review
	 * @param id the review id
	 * @param comments	
	 * @param reviewee the student that is being reviewed
	 * @param reviewer the tutor that writes the review
	 * @return a student review if every input is valid
	 */
	@Transactional
	public StudentReview createStudentReview(Integer id, String comments, Student reviewee, Tutor reviewer) {
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
		if(reviewer == null) {
			error +="Reviewer is null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		StudentReview sr = new StudentReview();
		sr.setId(id);
		sr.setReview(comments);
		sr.setReviewee(reviewee);
		sr.setAuthor(reviewer);
		studentReviewRepository.save(sr);
		return sr;
	}
	
	/**
	 * This is a method to update the review of a target student
	 * @param id the review id
	 * @param comments	
	 * @param reviewee the student that is being reviewed
	 * @return a student review if every input is valid
	 */
	
	@Transactional
	public StudentReview updateStudentReview(int id, String comments, Student reviewee) {
		String error = "";
		if(id < 0 ){
			error += "Incorrect id value. ";
		}
		if(reviewee == null){
			error += "Please insert a reviewee. ";
		}
		if(comments == null || comments.equals("")){
			error += "Please insert a comment. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		try {
			StudentReview review = studentReviewRepository.findById(id).get();
			review.setId(id);
			review.setReview(comments);
			review.setReviewee(reviewee);
			
			studentReviewRepository.save(review);
			return review;
			
		} catch(NoSuchElementException e ) {
			throw new IllegalArgumentException("Please enter a valid Review to update.");
		}
	}
	
	/**
	 * This is a method to create a new tutor review
	 * @param id the review id
	 * @param comments	
	 * @param reviewee the tutor that is being reviewed
	 * @param rating numerical rating of the tutor
	 * @param reviewer the student that writes the review
	 * @return a student review if every input is valid
	 */
	@Transactional
	public TutorReview createTutorReview(Integer id, String comments, Tutor reviewee, Integer rating, Student reviewer) {
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
		if(reviewer == null) {
			error +="Reviewer is null. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		TutorReview tr = new TutorReview();
		tr.setId(id);
		tr.setReview(comments);
		tr.setReviewee(reviewee);
		tr.setRating(rating);
		tr.setAuthor(reviewer);
		tutorReviewRepository.save(tr);
		return tr;
	}
	/**
	 * This is a method to get a student review by id
	 * @param id the review id
	 * @return a student review 
	 */
	@Transactional
	public StudentReview getStudentReview(Integer id) {
		if(id < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		StudentReview r = studentReviewRepository.findById(id).get();
		return r;
	}
	/**
	 * This is a method to get a tutor review by id
	 * @param id the review id
	 * @return a tutor review 
	 */
	@Transactional
	public TutorReview getTutorReview(Integer id) {
		if(id < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		TutorReview r = tutorReviewRepository.findById(id).get();
		return r;
	}
	/**
	 * This is a method to get all student reviews
	 * @return a list of all student reviews 
	 */
	@Transactional
	public List<StudentReview> getAllStudentReviews() {
		return toList(studentReviewRepository.findAll());
	}
	/**
	 * This is a method to get all tutor reviews
	 * @return a list of all tutor reviews 
	 */
	@Transactional
	public List<TutorReview> getAllTutorReviews() {
		return toList(tutorReviewRepository.findAll());
	}
	/**
	 * This is a method to create a new room
	 * @param roomNr room number
	 * @param isLargeRoom true if a room is a large room, false if a room is a small room	
	 * @return a room if every input is valid
	 */
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
	/**
	 * This is a method to get a room by room number
	 * @param id the review id
	 * @return a room with the input id
	 */
	@Transactional
	public Room getRoom(Integer roomNr) {
		if(roomNr < 0){
			throw new IllegalArgumentException("Room number is invalid");
		}
		Room r = roomRepository.findById(roomNr).get();
		return r;
	}
	/**
	 * This is a method to get all rooms
	 * @return a list of all the rooms in the backend 
	 */
	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}
	/**
	 * This is a method to get all large rooms
	 * @return a list of all the large rooms in the backend 
	 */
	@Transactional
	public List<Room> getAllLargeRooms(){
		List<Room> largeRooms = new ArrayList<Room>();
		for (Room r : roomRepository.findAll()) {
			if (r.getIsLargeRoom() == true) {
				largeRooms.add(r);
			}
		}
		
		return largeRooms;
	}
	/**
	 * This is a method to get all small rooms
	 * @return a list of all the small rooms in the backend 
	 */
	@Transactional
	public List<Room> getAllSmallRooms(){
		List<Room> smallRooms = new ArrayList<Room>();
		for (Room r : roomRepository.findAll()) {
			if (r.getIsLargeRoom() == false) {
				smallRooms.add(r);
			}
		}
		
		return smallRooms;
	}
	/**
	 * This is a method to set the room with sessions
	 * @param roomNr room number
	 * @param isLargeRoom true if a room is a large room, false if a room is a small room
	 * @param sessions a list of session 
	 * @param unavailability a list of roomBooking
	 * @return a room if every input is valid
	 */
	@Transactional
	public Room updateRoom(Integer roomNr, Boolean isLargeRoom, Set<Session> sessions, Set<RoomBooking> unavailability) {
		String error = "";
		if(roomNr < 0){
			error +="Room number is invalid. ";
		}
		if(isLargeRoom == null){
			error +="Room Type (isLarge = true || false) cannot be null. ";
		}
		if(sessions == null) {
			error += "Sessions invalid ";
		}
		if(unavailability == null) {
			error += "Unavailability invalid. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		
		Room r = roomRepository.findById(roomNr).get();
		
		r.setSession(sessions);	
		r.setUnavailability(unavailability);
		r.setIsLargeRoom(isLargeRoom);
		roomRepository.save(r);
		return r; 
	}
	/**
	 * This is a method delete a room with room number
	 * @param roomNr the room number of the room you want to delete
	 * @return true if the room is successfully deleted
	 */
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
	/**
	 * This is a method to create a room booking
	 * @param id the id of room booking stored in the backend 
	 * @param startTime start time
	 * @param endTime endtime
	 * @param date
	 * @return a room booling if all the inputs are valid
	 */
	
	@Transactional
	public RoomBooking createRoomBooking(Integer id, Time startTime, Time endTime, Date date) {
		String error = "";
		if(id == null || id < 0){
			error +="ID is invalid. ";
		}
		if(startTime == null){
			error +="Start time is null. ";
		}
		if(endTime == null){
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
	/**
	 * This is a method to update a room booking
	 * @param id the id of room booking you want ti update
	 * @param startTime start time
	 * @param endTime endtime
	 * @param date
	 * @return a room booling if all the inputs are valid
	 */
	@Transactional
	public RoomBooking updateRoomBooking (Integer id, Time startTime, Time endTime, Date date) {
		String error = "";
		if(id == null || id < 0){
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
			error += "Start time must be before End time.";
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
	/**
	 * This is a method to get a room booking by id
	 * @param id the id of the roombooking you want to get
	 * @return a room booking with the input id
	 */
	@Transactional
	public RoomBooking getRoomBooking(Integer id) {
		
		RoomBooking rb = roomBookingRepository.findById(id).get();
		return rb;
	}
	/**
	 * This is a method to get all room bookings
	 * @return a list of all room bookings in the backend
	 */
	@Transactional
	public List<RoomBooking> getAllRoomBookings(){
		return toList(roomBookingRepository.findAll());
	}
	/**
	 * This is a method to to create a seesion
	 * @param id the id of the session that will be stored in the backend
	 * @param isConfirmed a boolean that shows if the seesion is confirmed by the teacher
	 * @param startTime start time
	 * @param endTime endtime
	 * @param date
	 * @param isGroupSession true if the sesison is a group session
	 * @param tutee a list of students atending the session
	 * @param tutor a tutor who is going to teach the session
	 * @param room the room where the session takes place
	 * @param course the course that is taught
	 * @return a session if all the inputs are valid
	 */
	@Transactional
	public Session createSession(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Set <Student> tutee, Tutor tutor, Room room, Course course) {
	
		String error = "";
		if(id == null || id < 0) {
			error += "Id is invalid. ";
		}
		if(room == null){
			error += "Room number is invalid. ";
		}
		if(isConfirmed == null){
			error += "Is Confirmed is null. ";
		}
		if(startTime == null || startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))){
			error +="Start time is invalid. ";
		}
		if(startTime != null) {
			if(endTime == null || endTime.after(Time.valueOf("21:00:00")) || endTime.before(startTime)){
				error +="End time is invalid. ";
			}	
		}
		if(date == null){
			error +="Date is null. ";
		}
		if(isGroupSession == null){
			error +="Is group session is null. ";
		}
		for(Student s: tutee) {
			if(s == null) {
				error +="Tutee is null. ";
			}
		}
		if(tutee.size() == 0) {
			error += "Tutee is null. ";
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
		s.setStudent(tutee); //This causes a null pointer exception
		s.setTutor(tutor);
		s.setRoom(room);
		s.setCourse(course);
		sessionRepository.save(s);
		return s;
	}
	/**
	 * This is a method to to update a seesion
	 * @param id the id of the session
	 * @param isConfirmed a boolean that shows if the seesion is confirmed by the teacher
	 * @param startTime start time
	 * @param endTime endtime
	 * @param date
	 * @param isGroupSession true if the sesison is a group session
	 * @param tutee a list of students atending the session
	 * @param tutor a tutor who is going to teach the session
	 * @param room the room where the session takes place
	 * @param course the course that is taught
	 * @return a session if all the inputs are valid
	 */
	@Transactional
	public Session updateSession(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Set <Student> tutee, Tutor tutor, Room room, Course course) {
	
		String error = "";
		if(id == null || id < 0) {
			error += "Id is invalid. ";
		}
		if(room == null){
			error += "Room number is invalid. ";
		}
		if(isConfirmed == null){
			error += "Is Confirmed is null. ";
		}
		if(startTime == null || startTime.before(Time.valueOf("09:00:00")) || startTime.after(Time.valueOf("21:00:00"))){
			error +="Start time is invalid. ";
		}
		if(startTime != null) {
			if(endTime == null || endTime.after(Time.valueOf("21:00:00")) || endTime.before(startTime)){
				error +="End time is invalid. ";
			}	
		}
		if(date == null){
			error +="Date is null. ";
		}
		if(isGroupSession == null){
			error +="Is group session is null. ";
		}
		for(Student s: tutee) {
			if(s == null) {
				error +="Tutee is null. ";
			}
		}
		if(tutee.size() == 0) {
			error += "Tutee is null. ";
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
		s.setStartTime(startTime);
		s.setEndTime(endTime);
		s.setDate(date);
		s.setIsGroupSession(isGroupSession);
		s.setIsConfirmed(isConfirmed);
		s.setStudent(tutee); //This causes a null pointer exception
		s.setTutor(tutor);
		s.setRoom(room);
		s.setCourse(course);
		sessionRepository.save(s);
		return s;
	}
	/**
	 * This is a method to to update the status of a seesion
	 * @param id the id of the session 
	 * @param isConfirmed the status you want to update
	 * @return a session if all the inputs are valid
	 */
	@Transactional
	public Session updateSessionIsConfirmed(Integer id, Boolean isConfirmed) {
				
		Session s = sessionRepository.findById(id).get();
		s.setIsConfirmed(isConfirmed);
		sessionRepository.save(s);
		
		if (isConfirmed) {
			Tutor t = s.getTutor();
			Set<Session> pendingSessions = t.getPendingSession();
			Set<Session> currentSessions = t.getSession();
			currentSessions.add(s);
			pendingSessions.remove(s);
		}
		return s;
	}
	/**
	 * This is a method to get a session by a valid id
	 * @param id the id of the session you want to get
	 * @return a session  with the input id
	 */
	@Transactional
	public Session getSession(Integer id) {
		if(id == null || id < 0 ){
			throw new IllegalArgumentException("ID is invalid");
		}
		try {
			Session s = sessionRepository.findById(id).get();
			return s;
		}catch(Exception e) {
			throw new IllegalArgumentException("ID is invalid.");
		}

	}
	/**
	 * This is a method to get all session in the backend
	 * @return a list of all sessions in the backend
	 */
	@Transactional
	public List<Session> getAllSessions() {
		return toList(sessionRepository.findAll());
	}
	
	/**
	 * This is a method to remove a target session from the backend
	 * @param id the id of the session you want to delete
	 * @return a boolean shows if the session is successfully deleted
	 */
	
	@Transactional
	public boolean deleteSession(Integer sessionId) {
		if(sessionId == null){
			throw new IllegalArgumentException("SessionId must be valid. ");
		}
		boolean done = false;
		Session s = getSession(sessionId);
		if (s != null) {
			sessionRepository.delete(s);
			done = true;
		} else {
			throw new IllegalArgumentException("Session must exist. ");
		}
		return done;
	}
	/**
	 * This is a method to create a new course
	 * @param username the username will also be the id stored in the backend
	 * @param password the password is used to login
	 * @param name the name of the student
	 * @return a student if we all the inputs are correct
	 */
	@Transactional
	public Student createStudent(String username, String password, String name) {
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
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		
		Student s = new Student();
		s.setUsername(username);
		s.setPassword(password);
		s.setName(name);
		studentRepository.save(s);
		return s;
	}
	/**
	 * This is a method to create a new course
	 * @param username the username will also be the id stored in the backend
	 * @param password the password is used to login
	 * @param name the name of the student
	 * @param sessions a list of sessions the student attend 
	 * @return a student if we all the inputs are correct
	 */
	@Transactional
	public Student updateStudent(String username, String password, String name, Set <Session> sessions) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(name == null || name.trim().length() == 0){
			error +="New name can't be empty. ";
		}
		if(sessions == null){
			error += "Sessions can't be null. ";
		}
		if(error.length() != 0){
			throw new 
			IllegalArgumentException(error);
		}

		Student student = studentRepository.findStudentByUsername(username);
		if(student == null)
			throw new IllegalArgumentException("Please input a valid student.");
		student.setPassword(password);
		student.setName(name);
		student.setSession(sessions);
		studentRepository.save(student);
		return student;
	}
	/**
	 * This is a method to get all student
	 * @return a list of all students in the backend
	 */
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}
	/**
	 * This is a method to get a student by its username
	 * @param username the username of the student you want to get
	 * @return a student with the target username
	 */
	@Transactional
	public Student getStudent(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty.");
		}
		Student s = studentRepository.findStudentByUsername(username);
		if(s == null)
			throw new IllegalArgumentException("User does not exist.");
		return s;
	}
	/**
	 * This is a method to get a student by its username
	 * @param username the username of the student you want to get
	 * @return a student with the target username
	 */
	@Transactional
	public Student getStudentByUsername(String username) {
		Student s = new Student();
		s = studentRepository.findStudentByUsername(username);
		return s;
	}
	/**
	 * This is a method to delete a student by its username
	 * @param username the username of the student you want to delete
	 * @return a boolean showing if the operation is successful
	 */
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

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	/**
	 * This is a method to create a new tutor
	 * @param name the name of the tutor
	 * @param username the username will also be the id stored in the backend
	 * @param password the password is used to login
	 * @param hourlyRate hourly rate
	 * @return a tutor if we all the inputs are correct
	 */
	@Transactional
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
	/**
	 * This is a method to get a tutor by its username
	 * @param username the username of the tutor you want to get
	 * @return a tutor with the target username
	 */
	@Transactional
	public Tutor getTutor(String username) {
		if(username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		Tutor a = tutorRepository.findTutorByUsername(username);
		return a;
	}
	/**
	 * This is a method to get all tutors in the backend
	 * @return a list of all the tutors
	 */
	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}

	@Transactional
	public Tutor updateTutor(String username, String name, String password, double hourlyRate, Set<Session> pendingSessions, Set<Session> sessions, Set<Course> courses) {
		String error = "";
		if(username == null || username.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(password == null || password.trim().length() == 0){
			error +="Password can't be empty. ";
		}
		if(name == null || name.trim().length() == 0){
			error +="New name can't be empty. ";
		}
		if(hourlyRate < 0) {
			error +="Hourly rate is invalid. ";
		}
		if(sessions == null) {
			error += "Sessions are invalid. ";
		}
		if(pendingSessions == null) {
			error += "Pending sessions are invalid. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}

		Tutor tutor = tutorRepository.findTutorByUsername(username);
		if(tutor == null) {
			throw new IllegalArgumentException("Please enter a valid tutor to update");
			}
		
		tutor.setName(name);
		tutor.setPassword(password);
		tutor.setHourlyRate(hourlyRate);
		tutor.setSession(sessions);
		tutor.setPendingSession(pendingSessions);
		tutor.setCourse(courses);
		tutorRepository.save(tutor);
		return tutor;
	}
	/**
	 * This is a method to delete a tutor by its username
	 * @param username the username of the tutor you want to delete
	 * @return a boolean showing if the opration is succesful
	 */
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
	/**
	 * This is a method to create a new university
	 * @param id 
	 * @param name the name of the university
	 * @return a university if all the inputs are correct
	 */
	@Transactional
	public University createUniversity (int id, String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty. ");
		}
		University u = new University();
		u.setName(name);
		u.setId(id);
		universityRepository.save(u);
		return u;
	}
	/**
	 * This is a method to get a university by its name
	 * @param name the name of the tutor you want to get
	 * @return a university with the target name
	 */
	@Transactional
	public University getUniversity(String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty. ");
		}
		University u = universityRepository.findUniversityByName(name);
		if(u == null)
			throw new IllegalArgumentException("University does not exist.");
		return u;
	}
	/**
	 * This is a method to get all universities in the backend
	 * @return a list of all the universities
	 */
	@Transactional
	public List<University> getAllUniversities() {
		return toList(universityRepository.findAll());
	}
	
	/**
	 * This is a method to update a university
	 * @param id 
	 * @param name the name of the university
	 * @return a university if all the inputs are correct
	 */
	@Transactional
	public University updateUniversity(Integer id, String name) {
		String error = "";
		if(name == null || name.trim().length() == 0){
			error += "Name can't be empty. ";
		}		
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
		}
		
		University university = universityRepository.findById(id).get();
		university.setName(name);
		universityRepository.save(university);
		return university;
	}



}
