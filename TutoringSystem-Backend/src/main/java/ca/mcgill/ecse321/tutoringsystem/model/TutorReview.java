package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TutorReview extends Review{
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
   
   }
