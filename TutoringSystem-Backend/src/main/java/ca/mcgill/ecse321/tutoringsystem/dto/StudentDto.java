package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

public class StudentDto {
	
	private String name;
	private String username;
	private String password;
	private String schoolName;
	private Set<Session> session;
	private Set<TutorReview> tutorReview;
	
	public StudentDto() {
	}
	public StudentDto(String username, String password, String name, String schoolName) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.schoolName = schoolName;
	}
	
	public String getUsername() {
	    return username;
	}
	public String getName() {
		return name;
	}
	public Set<Session> getSession() {
		return session;
	}
	public void setSession(Set<Session> session) {
		this.session = session;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
