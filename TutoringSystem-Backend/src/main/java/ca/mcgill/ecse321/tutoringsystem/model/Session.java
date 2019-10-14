package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Session{
   private Boolean isConfirmed;

public void setIsConfirmed(Boolean value) {
    this.isConfirmed = value;
}
public Boolean getIsConfirmed() {
    return this.isConfirmed;
}
private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
private Time endTime;

public void setEndTime(Time value) {
    this.endTime = value;
}
public Time getEndTime() {
    return this.endTime;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Integer id;

public void setId(Integer value) {
    this.id = value;
}
@Id
public Integer getId() {
    return this.id;
}
private Set<Student> student;

@ManyToMany
public Set<Student> getStudent() {
   return this.student;
}

public void setStudent(Set<Student> students) {
   this.student = students;
}

private Boolean isGroupSession;

public void setIsGroupSession(Boolean value) {
    this.isGroupSession = value;
}
public Boolean getIsGroupSession() {
    return this.isGroupSession;
}
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Room room;
   
   @ManyToOne(optional=false)
   public Room getRoom() {
      return this.room;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   private Course course;
   
   @ManyToOne(optional=false)
   public Course getCourse() {
      return this.course;
   }
   
   public void setCourse(Course course) {
      this.course = course;
   }
   
   private TutoringSystem tutoringSystem;
   
   @ManyToOne(optional=false)
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   public void setTutoringSystem(TutoringSystem tutoringSystem) {
      this.tutoringSystem = tutoringSystem;
   }
   
   }
