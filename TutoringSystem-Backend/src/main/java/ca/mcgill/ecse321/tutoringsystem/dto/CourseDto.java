package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.model.Session;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.University;

public class CourseDto {
	private String courseCode;
	private String subject;
	private Set<Tutor> tutor;
    private University university;
    private Set<Session> session;
    
    public CourseDto() {
    }
    public CourseDto(String code, String sub, University u) {
    		this.courseCode = code;
    		this.subject = sub;
    		this.university = u;
    }
    
    public String getCourseCode() {
        return this.courseCode;
    }
    public String getSubject() {
        return this.subject;
    }
    public University getUniversity() {
        return this.university;
    }
}
