package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;


import javax.persistence.ManyToOne;

@Entity
public class University{
   private String name;
   private int id;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
@Id
public int getId() {
   return this.id;
}
public void setId(int id){
   this.id = id;
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
