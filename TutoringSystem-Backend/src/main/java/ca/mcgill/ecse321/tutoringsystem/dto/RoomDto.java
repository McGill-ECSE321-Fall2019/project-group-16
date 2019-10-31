package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Session;

public class RoomDto {
	private Integer roomNr;
	private Boolean isLargeRoom;
	private Set<Session> session;
	
	public RoomDto() {
	}
	
	public RoomDto(Integer roomNr, Boolean isLargeRoom, Set<Session> session) {
		this.roomNr = roomNr;
		this.isLargeRoom = isLargeRoom;
		this.session = session;
	}

	public Integer getRoomNr() {
		return this.roomNr;
	}
	public Boolean getIsLargeRoom() {
		return this.isLargeRoom;
	}
	public Set<Session> getSession(){
		return this.session;
	}
}
