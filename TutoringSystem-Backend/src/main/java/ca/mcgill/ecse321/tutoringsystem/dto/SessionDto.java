package ca.mcgill.ecse321.tutoringsystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public class SessionDto {

	private Boolean isConfirmed;
	private Time startTime;
	private Time endTime;
	private Date date;
	private Integer id;
	private Set<Student> student;
	private Boolean isGroupSession;
	private Tutor tutor;
	private Room room;
	private Course course;
	
	public SessionDto(Integer id, Boolean isConfirmed, Time startTime, Time endTime, Date date, Boolean isGroupSession, Set <Student> tutee, Tutor tutor, Room room, Course course) {
		this.id = id;
		this.isConfirmed=isConfirmed;
		this.startTime=startTime;
		this.endTime=endTime;
		this.date = date;
		this.isGroupSession=isGroupSession;
		this.student=tutee;
		this.tutor=tutor;
		this.room=room;
		this.course=course;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	public void setIsGroupSession(Boolean isGroupSession) {
		this.isGroupSession = isGroupSession;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Date getDate() {
		return date;
	}

	public Integer getId() {
		return id;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public Boolean getIsGroupSession() {
		return isGroupSession;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public Room getRoom() {
		return room;
	}

	public Course getCourse() {
		return course;
	}
	
	   
	
	
	
}
