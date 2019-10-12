package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StudentReview extends Review{
   private Tutor author;
   
   @ManyToOne(optional=false)
   public Tutor getAuthor() {
      return this.author;
   }
   
   public void setAuthor(Tutor author) {
      this.author = author;
   }
   
   }
