import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class TutorReview extends Review{
private Set<Student> student;
   
   @ManyToMany
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
private int rating;

public void setRating(int value) {
this.rating = value;
}
public int getRating() {
return this.rating;
}
   
   }
