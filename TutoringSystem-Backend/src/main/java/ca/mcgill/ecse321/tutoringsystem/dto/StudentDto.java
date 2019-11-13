package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

public class StudentDto {
	
	private String name;
	private String username;
	private String password;
	
	public StudentDto() {
	}
	
	public StudentDto(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
	    return this.username;
	}
	public String getName() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
