package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	private String courseCode;

	public void setCourseCode(String value) {
		this.courseCode = value;
	}

	@Id
	public String getCourseCode() {
		return this.courseCode;
	}

	private String subject;

	public void setSubject(String value) {
		this.subject = value;
	}

	public String getSubject() {
		return this.subject;
	}

	private Set<Tutor> tutor;

	@ManyToMany
	public Set<Tutor> getTutor() {
		return this.tutor;
	}

	public void setTutor(Set<Tutor> tutors) {
		this.tutor = tutors;
	}

	private University university;

	@ManyToOne(optional = false)
	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	private Set<Session> session;

	@OneToMany(mappedBy = "course")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}
	
	private Boolean isRequested = false;
	
	public void setIsRequested(boolean flag) {
		isRequested = flag;
	}
	
	public Boolean getIsRequested() {
		return this.isRequested;
	}

}
