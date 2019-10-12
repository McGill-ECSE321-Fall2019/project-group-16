import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.Entity;

@Entity
public class User{
private String name;

public void setName(String value) {
this.name = value;
}
public String getName() {
return this.name;
}
private Set<Review> review;

@ManyToMany
public Set<Review> getReview() {
   return this.review;
}

public void setReview(Set<Review> reviews) {
   this.review = reviews;
}

   private String username;

public void setUsername(String value) {
    this.username = value;
}
public String getUsername() {
    return this.username;
}
private int password;

public void setPassword(int value) {
    this.password = value;
}
public int getPassword() {
    return this.password;
}
}
