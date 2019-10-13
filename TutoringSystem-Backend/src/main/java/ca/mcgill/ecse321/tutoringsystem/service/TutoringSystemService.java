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
		StudentReview sr = new StudentReview();
		sr.setId(id);
		sr.setReview(comments);
		sr.setReviewee(reviewee);
		reviewRepository.save(sr);
		return sr;
	}
	
	
	@Transactional
	public Review createTutorReview(Integer id, String comments, Tutor reviewee, Integer rating) {
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
	public Student createStudent(String username, String password, String name, String schoolName) {
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
		User u = userRepository.findById(username).get();
		return u;
	}
	
	@Transactional
	public University createUniversity(String name) {
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
	
	
}
