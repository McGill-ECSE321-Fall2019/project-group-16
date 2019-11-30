package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.RoomBooking;

public interface RoomBookingRepository extends CrudRepository<RoomBooking, Integer> {
	//RoomBooking findRoomBookingById(Integer id);
}