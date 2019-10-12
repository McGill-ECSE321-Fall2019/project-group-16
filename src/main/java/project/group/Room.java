import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Room{
private int roomNr;

public void setRoomNr(int value) {
this.roomNr = value;
}
public int getRoomNr() {
return this.roomNr;
}
private Set<RoomAvailability> roomAvailability;

@ManyToMany
public Set<RoomAvailability> getRoomAvailability() {
   return this.roomAvailability;
}

public void setRoomAvailability(Set<RoomAvailability> roomAvailabilitys) {
   this.roomAvailability = roomAvailabilitys;
}

   private boolean avalability;

public void setAvalability(boolean value) {
    this.avalability = value;
}
public boolean isAvalability() {
    return this.avalability;
}
private Set<Session> session;

@OneToMany(mappedBy="room" )
public Set<Session> getSession() {
   return this.session;
}

public void setSession(Set<Session> sessions) {
   this.session = sessions;
}

private boolean isLargeRoom;

public void setIsLargeRoom(boolean value) {
    this.isLargeRoom = value;
}
public boolean isIsLargeRoom() {
    return this.isLargeRoom;
}
}
