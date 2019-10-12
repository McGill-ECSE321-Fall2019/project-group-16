import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Subject{
   private int courseNr;

public void setCourseNr(int value) {
    this.courseNr = value;
}
public int getCourseNr() {
    return this.courseNr;
}
private String university;

public void setUniversity(String value) {
    this.university = value;
}
public String getUniversity() {
    return this.university;
}
   private Set<Tutor> tutor;
   
   @ManyToMany
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   }
