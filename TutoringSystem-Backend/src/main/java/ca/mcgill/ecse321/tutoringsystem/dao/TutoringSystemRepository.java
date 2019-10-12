package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringsystem.model.*;

@Repository
public class TutoringSystemRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Course createCourse(String courseCode, String subject) {
		Course c = new Course();
		c.setCourseCode(courseCode);
		c.setSubject(subject);
		entityManager.persist(c);
		return c;
	}
	
	@Transactional
	public Course getPerson(String courseCode) {
		Course c = entityManager.find(Course.class, courseCode);
		return c;
	}
	
//	@Transactional
//	public Review 
	
}
