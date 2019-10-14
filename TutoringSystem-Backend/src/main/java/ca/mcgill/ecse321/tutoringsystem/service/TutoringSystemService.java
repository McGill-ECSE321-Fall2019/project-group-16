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
	
	@Transactional
	public Course createCourse(String courseCode, String subject, University university) {
		String error = "";
		if(courseCode == null || courseCode.trim().length() == 0){
			error +="Course can't be empty. ";
		}
		if(subject == null || subject.trim().length() == 0){
			error +="Subject can't be empty. ";
		}
		if(university == null){
			error +="University is null. ";
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
	public Course getCourse(String courseCode) {
		if(courseCode == null || courseCode.trim().length() == 0){
			throw new IllegalArgumentException("Course can't be empty");
		}
		Course c = courseRepository.findByCourseCode(courseCode);
		return c;
	}
	
	@Transactional
	public Review createStudentReview(Integer id, String comments, Student reviewee) {
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
		sr.setReviewee(reviewee);
		reviewRepository.save(sr);
		return sr;
	}
	
	
	@Transactional
	public Review createTutorReview(Integer id, String comments, Tutor reviewee, Integer rating) {
		if(id < 0){
			error +="ID is invalid. ";
		}
		if(comments == null || comments.trim().length() == 0){
			error +="Comments can't be empty. ";
		}
		if(reviewee == null){
			error +="Reviewee is null. ";
		}
		if(rating < 0){
			error +="Rating is invalid. ";
		}
		if(error.length() != 0){
			throw new IllegalArgumentException(error);
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
		if(rating < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		Review r = reviewRepository.findById(id).get();
		return r;
	}
	
	@Transactional
	public Room createRoom(Integer roomNr, Boolean isLargeRoom) {
		String error = "";
		if(roomNr < 0){
			error +="Room number is invalid. ";
		}
		if(isLargeRoom == null){
			error +="Is large room is null. ";
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
	public RoomBooking createRoomBooking(Integer id, Time startTime, Time endTime, Date date) {
		String error = "";
		if(id < 0){
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
		if(id < 0){
			throw new IllegalArgumentException("ID is invalid");
		}
		RoomBooking rb = roomBookingRepository.findById(id).get();
		return rb;
	}
	
	@Transactional
	public Session createSession(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Student tutee, Tutor tutor, Room room, Course course) {
		String error = "";
		if(roomNr < 0){
			error += "Room number is invalid. ";
		}
		if(isConfirmed == null){
			error += "Is Confirmed is null. ";
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
	public Student createStudent(String username, String password, String name, String schoolName) {
		String error = "";
		if(username == null || user.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(PasswordView == null || password.trim().length() == 0){
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
		userRepository.save(s);
		return s;
	}
	
	@Transactional
	public Tutor createTutor(String username, String password, String name, Double hourlyRate) {
		String error = "";
		if(username == null || user.trim().length() == 0){
			error +="Username can't be empty. ";
		}
		if(PasswordView == null || password.trim().length() == 0){
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
		userRepository.save(t);
		return t;
	}
	
	@Transactional
	public User getUser(String username) {
		if(username == null || user.trim().length() == 0){
			throw new IllegalArgumentException("Username can't be empty. ");
		}
		User u = userRepository.findById(username).get();
		return u;
	}
	
	@Transactional
	public University createUniversity(String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty");
		}
		University u = new University();
		u.setName(name);
		universityRepository.save(u);
		return u;
	}
	
	@Transactional
	public University getUniversity(String name) {
		if(name == null || name.trim().length() == 0){
			throw new IllegalArgumentException("Name can't be empty");
		}
		University u = universityRepository.findById(name).get();
		return u;
	}
	
	
}
