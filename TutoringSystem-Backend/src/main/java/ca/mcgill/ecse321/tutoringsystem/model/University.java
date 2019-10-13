package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class University {
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
	public String getName() {
		return this.name;
	}

	private Set<Course> course;

	@OneToMany(mappedBy = "university", cascade = { CascadeType.ALL })
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	private TutoringSystem tutoringSystem;

	@ManyToOne(optional = false)
	public TutoringSystem getTutoringSystem() {
		return this.tutoringSystem;
	}

	public void setTutoringSystem(TutoringSystem tutoringSystem) {
		this.tutoringSystem = tutoringSystem;
	}

}
