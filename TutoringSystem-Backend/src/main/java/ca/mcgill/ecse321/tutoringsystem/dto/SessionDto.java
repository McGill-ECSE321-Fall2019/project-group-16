package ca.mcgill.ecse321.tutoringsystem.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;


public class SessionDto {
	
	private Time startTime;
	private Time endTime;
	private Date date;
	private boolean isConfirmed;
	private int id;
	private boolean isGroupSession;
	
    
    public SessionDto() {
    }
    
    public SessionDto(Time startTime, Time endTime, Date date, int id, boolean isGroupSession, boolean isConfirmed) {
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.date = date;
    	this.id = id;
    	this.isGroupSession = isGroupSession;
    	this.isConfirmed = isConfirmed;
    }
    
    
    public Time getStartTime() {
        return this.startTime;
    }
    public Time getEndTime() {
        return this.endTime;
    }
    public Date getDate() {
    	return this.date;
    }
    public int getId() {
    	return this.id;
    }
    public boolean getIsGroupSession() {
    	return this.isGroupSession;
    }
    public boolean getIsConfirmed() {
    	return this.isConfirmed;
    }
  
}
