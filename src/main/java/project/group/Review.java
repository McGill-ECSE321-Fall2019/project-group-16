import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Review{
   private String review;

public void setReview(String value) {
    this.review = value;
}
public String getReview() {
    return this.review;
}
   private Set<User> user;
   
   @ManyToMany(mappedBy="review" )
   public Set<User> getUser() {
      return this.user;
   }
   
   public void setUser(Set<User> users) {
      this.user = users;
   }
   
   }
