package ca.mcgill.ecse321.tutoringsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class RoomBookingDto {
	private Integer id;
	private Date date;
	private Time startTime;
	private Time endTime;
	
	public RoomBookingDto(Integer id, Date date, Time startTime, Time endTime) {
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setStartTime(Time time) {
		this.startTime = time;
	}
	
	public void setEndTime(Time time) {
		this.endTime = time;
	}	
}
