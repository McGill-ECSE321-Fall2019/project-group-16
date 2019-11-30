package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, String>{
	
	Tutor findTutorByUsername(String username);

}
