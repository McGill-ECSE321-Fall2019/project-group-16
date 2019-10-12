import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class University{
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
   private Set<Course> course;
   
   @OneToMany(mappedBy="university" , cascade={CascadeType.ALL})
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   }
