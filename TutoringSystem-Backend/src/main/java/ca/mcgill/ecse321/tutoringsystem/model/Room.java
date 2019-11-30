package ca.mcgill.ecse321.tutoringsystem.model;
//import RoomBooking;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Room {
	private Integer roomNr;

	public void setRoomNr(Integer value) {
		this.roomNr = value;
	}

	@Id
	public Integer getRoomNr() {
		return this.roomNr;
	}

	private Boolean isLargeRoom;

	public void setIsLargeRoom(Boolean value) {
		this.isLargeRoom = value;
	}

	public Boolean getIsLargeRoom() {
		return this.isLargeRoom;
	}

	private Set<RoomBooking> unavailability;

	@OneToMany
	public Set<RoomBooking> getUnavailability() {
		return this.unavailability;
	}

	public void setUnavailability(Set<RoomBooking> unavailabilitys) {
		this.unavailability = unavailabilitys;
	}

	private Set<Session> session;

	@OneToMany(mappedBy = "room")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

}
