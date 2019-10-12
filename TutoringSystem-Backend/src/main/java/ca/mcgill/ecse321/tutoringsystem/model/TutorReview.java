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
   private Student tutor;
   
   @ManyToOne(optional=false)
   public Student getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Student tutor) {
      this.tutor = tutor;
   }
   
   }
