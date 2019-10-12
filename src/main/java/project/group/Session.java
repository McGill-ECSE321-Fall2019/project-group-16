import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Session{
   private boolean isConfirmed;

public void setIsConfirmed(boolean value) {
    this.isConfirmed = value;
}
public boolean isIsConfirmed() {
    return this.isConfirmed;
}
private Set<Student> tutee;

@ManyToMany
public Set<Student> getTutee() {
   return this.tutee;
}

public void setTutee(Set<Student> tutees) {
   this.tutee = tutees;
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

private String startTime;

public void setStartTime(String value) {
    this.startTime = value;
}
public String getStartTime() {
    return this.startTime;
}
private String endTime;

public void setEndTime(String value) {
    this.endTime = value;
}
public String getEndTime() {
    return this.endTime;
}
}
