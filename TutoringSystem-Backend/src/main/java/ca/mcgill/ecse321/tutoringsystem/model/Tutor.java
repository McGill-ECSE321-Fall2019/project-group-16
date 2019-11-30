package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
public class Tutor {
	private Double hourlyRate;

	public void setHourlyRate(Double value) {
		this.hourlyRate = value;
	}

	public Double getHourlyRate() {
		return this.hourlyRate;
	}

	private Set<StudentReview> studentReview;

	@OneToMany(mappedBy = "author")
	public Set<StudentReview> getStudentReview() {
		return this.studentReview;
	}

	public void setStudentReview(Set<StudentReview> studentReviews) {
		this.studentReview = studentReviews;
	}

	private Set<Session> session;

	@OneToMany(mappedBy = "tutor")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}
	
	private Set<Session> pendingSession;

	@OneToMany(mappedBy = "tutor")
	public Set<Session> getPendingSession() {
		return this.pendingSession;
	}

	public void setPendingSession(Set<Session> sessions) {
		this.pendingSession = sessions;
	}

	private Set<Course> course;

	@ManyToMany(mappedBy = "tutor")
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	private String username;

	public void setUsername(String value) {
		this.username = value;
	}

	@Id
	public String getUsername() {
		return this.username;
	}

	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private Set<TutorReview> tutorReview;

	@OneToMany(mappedBy = "reviewee")
	public Set<TutorReview> getTutorReview() {
		return this.tutorReview;
	}

	public void setTutorReview(Set<TutorReview> tutorReviews) {
		this.tutorReview = tutorReviews;
	}

}
