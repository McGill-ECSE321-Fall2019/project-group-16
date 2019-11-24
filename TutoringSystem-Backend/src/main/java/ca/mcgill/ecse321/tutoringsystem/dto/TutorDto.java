package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set; 

import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

public class TutorDto {
	
	private String name;
	private Double hourlyRate;
	private Set<StudentReview> studentReview;
	private Set<Session> session;
	private Set<Course> course;
	private String username;
	//private String password;
	//private Set<TutorReview> tutorReview;
	
	
	public TutorDto (String username, String name, Double hourlyRate, Set<StudentReview> studentReview, Set<Course> course) {
		this.username = username;
		this.name=name;
		this.hourlyRate=hourlyRate;
		this.studentReview=studentReview;
		this.course=course;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void setStudentReview(Set<StudentReview> studentReview) {
		this.studentReview = studentReview;
	}

	public void setSession(Set<Session> session) {
		this.session = session;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return this.username;
	}
	public String getName () {
		return name;
	}
	public Double getHourlyRate() {
		return hourlyRate;
	}
	public Set<StudentReview> getStudentReviews (){
		return studentReview;
	}
	public Set<Session> getAllSessions(){
		return session;
	}
	public Set<Course> getAllCourses (){
		return course;
	}	

}
