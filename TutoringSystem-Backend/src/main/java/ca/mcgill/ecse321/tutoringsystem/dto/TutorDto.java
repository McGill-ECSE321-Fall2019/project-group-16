package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set; 

import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

public class TutorDto {
	
	private String name;
	private Double hourlyRate;
	private String username;	
	private Set<TutorReviewDto> tutorReview;
	private Set<SessionDto> session;
	private Set<CourseDto> course;
	
	
	
	public TutorDto (String username, String name, Double hourlyRate, Set<TutorReviewDto> tutorReview, Set<CourseDto> course) {
		this.username = username;
		this.name=name;
		this.hourlyRate=hourlyRate;
		this.tutorReview=tutorReview;
		this.course=course;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void setTutorReview(Set<TutorReviewDto> tutorReview) {
		this.tutorReview = tutorReview;
	}

	public void setSession(Set<SessionDto> session) {
		this.session = session;
	}

	public void setCourse(Set<CourseDto> course) {
		this.course = course;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName () {
		return name;
	}
	public Double getHourlyRate() {
		return hourlyRate;
	}
	public Set<TutorReviewDto> getTutorReviews (){
		return tutorReview;
	}
	public Set<SessionDto> getAllSessions(){
		return session;
	}
	public Set<CourseDto> getAllCourses (){
		return course;
	}
	public String getUsername() {
		return username;
		
	}
 
}
