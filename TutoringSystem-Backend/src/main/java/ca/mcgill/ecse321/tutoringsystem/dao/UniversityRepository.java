package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.University;

public interface UniversityRepository extends CrudRepository<University, String> {
	University findUniversityByUniversityName(String name);
}