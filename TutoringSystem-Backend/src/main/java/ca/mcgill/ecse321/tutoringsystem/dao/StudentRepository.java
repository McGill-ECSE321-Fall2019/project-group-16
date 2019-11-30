package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Student;
//import ca.mcgill.ecse321.tutoringsystem.model.User;

public interface StudentRepository extends CrudRepository<Student, String>{
	
    Student findStudentByUsername(String username);
	
	//<List>Student findStudentByUser(User user);

}
