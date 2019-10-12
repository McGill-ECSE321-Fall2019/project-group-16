import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class StudentReview extends Review{
private Set<Tutor> author;
   
   @ManyToMany(mappedBy="studentReview" )
   public Set<Tutor> getAuthor() {
      return this.author;
   }
   
   public void setAuthor(Set<Tutor> authors) {
      this.author = authors;
   }
   
      
   }
