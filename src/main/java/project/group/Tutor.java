import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends User{
private Set<StudentReview> studentReview;
   
   @ManyToMany
   public Set<StudentReview> getStudentReview() {
      return this.studentReview;
   }
   
   public void setStudentReview(Set<StudentReview> studentReviews) {
      this.studentReview = studentReviews;
   }
   
   private Set<TutorAvailability> tutorAvailability;
   
   @ManyToMany
   public Set<TutorAvailability> getTutorAvailability() {
      return this.tutorAvailability;
   }
   
   public void setTutorAvailability(Set<TutorAvailability> tutorAvailabilitys) {
      this.tutorAvailability = tutorAvailabilitys;
   }
   
   private double hourlyRate;

public void setHourlyRate(double value) {
    this.hourlyRate = value;
}
public double getHourlyRate() {
    return this.hourlyRate;
}
   
   private Set<Session> session;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   private Set<Course> subject;
   
   @ManyToMany
   public Set<Course> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Course> subjects) {
      this.subject = subjects;
   }
   
   }
