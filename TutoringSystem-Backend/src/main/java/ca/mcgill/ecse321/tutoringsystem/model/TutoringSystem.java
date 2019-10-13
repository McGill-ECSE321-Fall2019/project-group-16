package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class TutoringSystem {
	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private Set<User> user;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<User> getUser() {
		return this.user;
	}

	public void setUser(Set<User> users) {
		this.user = users;
	}

	private Set<Session> session;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

	private Set<Room> room;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<Room> getRoom() {
		return this.room;
	}

	public void setRoom(Set<Room> rooms) {
		this.room = rooms;
	}

	private Set<RoomBooking> roomBooking;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<RoomBooking> getRoomBooking() {
		return this.roomBooking;
	}

	public void setRoomBooking(Set<RoomBooking> roomBookings) {
		this.roomBooking = roomBookings;
	}

	private Set<Review> review;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}

	private Set<University> university;

	@OneToMany(mappedBy = "tutoringSystem", cascade = { CascadeType.ALL })
	public Set<University> getUniversity() {
		return this.university;
	}

	public void setUniversity(Set<University> universitys) {
		this.university = universitys;
	}

}
