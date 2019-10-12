import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Student extends User{
private Set<TutorReview> author;
   
   @ManyToMany(mappedBy="student" )
   public Set<TutorReview> getAuthor() {
      return this.author;
   }
   
   public void setAuthor(Set<TutorReview> authors) {
      this.author = authors;
   }
   
   private Set<Session> session;
   
   @ManyToMany(mappedBy="tutee" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   private String schoolName;

public void setSchoolName(String value) {
    this.schoolName = value;
}
public String getSchoolName() {
    return this.schoolName;
}
}
