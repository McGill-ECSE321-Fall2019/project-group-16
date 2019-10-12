package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends User{
   private Real hourlyRate;

public void setHourlyRate(Real value) {
    this.hourlyRate = value;
}
public Real getHourlyRate() {
    return this.hourlyRate;
}
   private Set<StudentReview> studentReview;
   
   @OneToMany(mappedBy="author" )
   public Set<StudentReview> getStudentReview() {
      return this.studentReview;
   }
   
   public void setStudentReview(Set<StudentReview> studentReviews) {
      this.studentReview = studentReviews;
   }
   
   private Set<Session> session;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   private Set<Course> course;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   }
