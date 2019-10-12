import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Course{
private Set<Tutor> tutor;

public void setTutor(Set<Tutor> value) {
this.tutor = value;
}
public Set<Tutor> getTutor() {
return this.tutor;
}
private University university;

@ManyToOne(optional=false)
public University getUniversity() {
   return this.university;
}

public void setUniversity(University university) {
   this.university = university;
}

   private int courseNr;

public void setCourseNr(int value) {
    this.courseNr = value;
}
public int getCourseNr() {
    return this.courseNr;
}

private Set<String> subject;

public void setSubject(Set<String> value) {
    this.subject = value;
}
public Set<String> getSubject() {
    return this.subject;
}
}
