package ca.mcgill.ecse321.tutoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.model.Student;

@RestController
@SpringBootApplication
public class TutoringSystemApplication {
	
	private static Student CurrentlyLoggedInStudent;
	
	public static void main(String[] args) {
		SpringApplication.run(TutoringSystemApplication.class, args);
	}
	
	@RequestMapping("/")
	public String greeting() {
		return "Welcome to Tutoring System group 16";
	}
	
	public static Student getCurrentlyLoggedInStudent() {
		return CurrentlyLoggedInStudent;
	}

	public static void setCurrentlyLoggedInStudent(Student currentlyLoggedInStudent) {
		CurrentlyLoggedInStudent = currentlyLoggedInStudent;
	}

}
