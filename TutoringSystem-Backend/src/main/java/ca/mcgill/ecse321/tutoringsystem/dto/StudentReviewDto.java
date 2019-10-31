package ca.mcgill.ecse321.tutoringsystem.dto;

import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public class StudentReviewDto {
	private int id;
	private String review;
	private Tutor author;
	private Student reviewee;
	private String authorName;
	private String revieweeName;
	
	

	public StudentReviewDto(int id, String review, Tutor author, Student reviewee) {
		this.id = id;
		this.review = review;
		this.authorName = author.getName();
		this.revieweeName = reviewee.getName();	
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
