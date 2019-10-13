package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {
	private String schoolName;

	public void setSchoolName(String value) {
		this.schoolName = value;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	private Set<Session> session;

	@ManyToMany(mappedBy = "student")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

	private Set<TutorReview> tutorReview;

	@OneToMany(mappedBy = "author")
	public Set<TutorReview> getTutorReview() {
		return this.tutorReview;
	}

	public void setTutorReview(Set<TutorReview> tutorReviews) {
		this.tutorReview = tutorReviews;
	}

}
