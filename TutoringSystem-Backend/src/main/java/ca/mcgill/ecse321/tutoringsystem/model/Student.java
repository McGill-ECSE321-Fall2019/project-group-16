package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Student{
   private String schoolName;

public void setSchoolName(String value) {
    this.schoolName = value;
}
public String getSchoolName() {
    return this.schoolName;
}
private Set<Session> session;

@ManyToMany(mappedBy="student" )
public Set<Session> getSession() {
   return this.session;
}

public void setSession(Set<Session> sessions) {
   this.session = sessions;
}

private Set<TutorReview> tutorReview;

@OneToMany(mappedBy="author" )
public Set<TutorReview> getTutorReview() {
   return this.tutorReview;
}

public void setTutorReview(Set<TutorReview> tutorReviews) {
   this.tutorReview = tutorReviews;
}

private String username;

public void setUsername(String value) {
    this.username = value;
}
@Id
public String getUsername() {
    return this.username;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
   private Set<Review> review;
   
   @OneToMany(mappedBy="studentReviewee" )
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   }
