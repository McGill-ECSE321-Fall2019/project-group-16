package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

	Course findByCourseCode(String courseCode);

	
}
