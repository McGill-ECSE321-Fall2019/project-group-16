package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringsystem.model.TutorReview;

public interface TutorReviewRepository extends CrudRepository<TutorReview, Integer> {
	//Review findReviewByReviewID(Integer reviewID);

}