package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringsystem.model.StudentReview;

public interface StudentReviewRepository extends CrudRepository<StudentReview, Integer> {
	//Review findReviewByReviewID(Integer reviewID);

}