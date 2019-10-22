package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	private String review;

	public void setReview(String value) {
		this.review = value;
	}

	public String getReview() {
		return this.review;
	}

	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private Student studentReviewee;

	@ManyToOne(optional = false)
	public Student getStudentReviewee() {
		return this.studentReviewee;
	}

	public void setStudentReviewee(Student studentReviewee) {
		this.studentReviewee = studentReviewee;
	}

	private Tutor tutorReviewee;

	@ManyToOne(optional = false)
	public Tutor getTutorReviewee() {
		return this.tutorReviewee;
	}

	public void setTutorReviewee(Tutor tutorReviewee) {
		this.tutorReviewee = tutorReviewee;
	}

}
