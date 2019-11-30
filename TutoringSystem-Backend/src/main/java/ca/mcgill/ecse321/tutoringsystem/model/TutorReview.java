package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class TutorReview{
   private Integer rating;

public void setRating(Integer value) {
    this.rating = value;
}
public Integer getRating() {
    return this.rating;
}
private Student author;

@ManyToOne(optional=false)
public Student getAuthor() {
   return this.author;
}

public void setAuthor(Student author) {
   this.author = author;
}

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
   private Tutor reviewee;
   
   @ManyToOne(optional=false)
   public Tutor getReviewee() {
      return this.reviewee;
   }
   
   public void setReviewee(Tutor reviewee) {
      this.reviewee = reviewee;
   }
   
   }
