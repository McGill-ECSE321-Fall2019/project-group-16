package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Course;

public class UniversityDto {
	private String name;
	private int id;
	private Set<Course> course;
	
	public UniversityDto(){
	}
	public UniversityDto(int id , String name){
		this.name = name;
		this.id = id;
	}

	public String getName() {
	   return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
}
