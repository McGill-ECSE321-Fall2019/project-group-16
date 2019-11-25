package ca.mcgill.ecse321.tutoringsystem.dto;

import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public class TutorReviewDto {
	private int id;
	private String review;
	private Tutor reviewee;
	private int rating;
	private  Student author;
	
	private String authorName;
	private String revieweeName;
	
	

	public TutorReviewDto(int id, String review, Tutor reviewee, int rating, Student author) {
		this.id = id;
		this.review = review;
		this.authorName = author.getName();
		this.revieweeName = reviewee.getName();	
		this.rating = rating;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	public String getReview() {
		return review;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

}
