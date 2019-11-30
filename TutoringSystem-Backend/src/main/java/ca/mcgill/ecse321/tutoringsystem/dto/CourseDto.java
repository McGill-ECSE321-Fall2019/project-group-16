package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.University;

public class CourseDto {
	private String courseCode;
	private String subject;
	private Set<Tutor> tutor;
    private int universityID;
    private String universityName;
    private Set<Session> session;
    
    public CourseDto() {
    }
    public CourseDto(String code, String sub, String universityName) {
    		this.courseCode = code;
    		this.subject = sub;
    		this.universityName = universityName;
    }
    
    public String getCourseCode() {
        return this.courseCode;
    }
    public String getSubject() {
        return this.subject;
    }
    public int getUniversityID() {
        return this.universityID;
    }
    public String getUniversityName() {
    		return this.universityName;
    }
}
