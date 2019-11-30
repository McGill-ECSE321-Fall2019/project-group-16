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
	private Set<String> studentName;
	private Boolean isGroupSession;
	private String tutorName;
	private int roomNr;
	private String courseCode;
	
	public SessionDto(Integer id, Set <String> studentName, String tutorName, int roomNr, String courseCode, Date date, Time startTime, Time endTime, Boolean isGroupSession, Boolean isConfirmed) {
		this.id = id;
		this.isConfirmed=isConfirmed;
		this.startTime=startTime;
		this.endTime=endTime;
		this.date = date;
		this.isGroupSession=isGroupSession;
		this.studentName=studentName;
		this.tutorName=tutorName;
		this.roomNr=roomNr;
		this.courseCode=courseCode;
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

	public void setStudent(Set<String> studentName) {
		this.studentName = studentName;
	}

	public void setIsGroupSession(Boolean isGroupSession) {
		this.isGroupSession = isGroupSession;
	}

	public void setTutor(String tutorName) {
		this.tutorName = tutorName;
	}

	public void setRoom(int roomNr) {
		this.roomNr = roomNr;
	}

	public void setCourse(String courseCode) {
		this.courseCode = courseCode;
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

	public Set<String> getStudent() {
		return studentName;
	}

	public Boolean getIsGroupSession() {
		return isGroupSession;
	}

	public String getTutor() {
		return tutorName;
	}

	public int getRoom() {
		return roomNr;
	}

	public String getCourse() {
		return courseCode;
	}
	
	   
}
