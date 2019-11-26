package ca.mcgill.ecse321.tutoringsystem.dto;

import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public class TutorReviewDto {
	private int id;
	private String review;
	private String reviewee;
	private int rating;
	private String author;
	
	

	public TutorReviewDto(int id, String review, String reviewee, int rating, String author) {
		this.id = id;
		this.review = review;
		this.author = author;
		this.reviewee = reviewee;	
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
	public int getRating(){
		return rating;
	}
	public void setRating(int rating){
		this.rating = rating;
	}

}
