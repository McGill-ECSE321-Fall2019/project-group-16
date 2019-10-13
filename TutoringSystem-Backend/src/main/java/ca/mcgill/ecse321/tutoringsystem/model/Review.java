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

	private User reviewee;

	@ManyToOne(optional = false)
	public User getReviewee() {
		return this.reviewee;
	}

	public void setReviewee(User reviewee) {
		this.reviewee = reviewee;
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
