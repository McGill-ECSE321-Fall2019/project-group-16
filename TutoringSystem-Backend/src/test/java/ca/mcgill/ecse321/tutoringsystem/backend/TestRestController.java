package ca.mcgill.ecse321.tutoringsystem.backend;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.UniversityRepository;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.TutoringSystemService;

@RunWith(MockitoJUnitRunner.class)
public class TestRestController {

	@Mock
	private StudentRepository studentDao;
	
	@Mock
	private UniversityRepository universityDao;
	
	@Mock
	private CourseRepository courseDao;
	
	@Mock
	private TutorRepository tutorDao;
	
	@Mock
	private StudentReviewRepository studentReviewDao;
	
	
	@InjectMocks
	private TutoringSystemService service;
	
	private static final String STUDENT_USERNAME = "TestStudent";
	private static final String TUTOR_USERNAME = "TestSTutor";
	private static final String NONEXISTING_USERNAME = "NotAStudent";
	private static final String STUDENT_NAME ="TestName";
	private static final String Tutor_NAME ="TestName2";
	private static final String NONEXISTING_NAME ="NotAName";
	private static final String STUDENT_PASS ="TestPass";
	private static final String Tutor_PASS ="TestPass2";
	private static final String NONEXISTING_PASS ="NotAPass";
	private static final String STUDENT_SCHOOLNAME ="TestPass";
	private static final String NONEXISTING_SCHOOLNAME ="NotAPass";

	private static final String UNI_NAME = "TestUniName";
	private static final int UNI_ID = 1;
	
	private static final String TUT_NAME = "Rijul_Saini";
	private static final String TUT_USERNAME = "rijul.saini";
	private static final String TUT_PWD = "password";
	private static final Double TUT_RATE = 18.00;
			

	private static final String COURSE_CODE = "TestCode";
	private static final String COURSE_SUB ="TestSubject";
	
	
	@Before
	public void setMockOutput() {
		//setMockOutputStudent();
		setMockOutputUniversity();
		setMockOutputAllCourse();
		setMockOutputCourse();
	}




	private void setMockOutputStudent() {
		when(studentDao.findStudentByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(STUDENT_USERNAME)) {
				Student student = new Student();
				student.setUsername(STUDENT_USERNAME);
				student.setName(STUDENT_NAME);
				student.setPassword(STUDENT_PASS);
				return student;
			} else {
			return null;
			}
		});
	}
	private void setMockOutputTutor2() {
		when(tutorDao.findTutorByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR_USERNAME)) {
				Tutor tutor = new Tutor();
				tutor.setUsername(TUTOR_USERNAME);
				tutor.setName(STUDENT_NAME);
				tutor.setPassword(STUDENT_PASS);
				tutor.setHourlyRate(1.0);
				return tutor;
			} else {
			return null;
			}
		});
	}
	

	private void setMockOutputUniversity() {
		when(universityDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<University> universities = new ArrayList<>();
			University uni = new University();
			uni.setName(UNI_NAME);
			uni.setId(UNI_ID);
			universities.add(uni);
			return universities;
		});	
	}
	
	private void setMockOutputUniversityEmpty() {
		when(universityDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<University> universities = new ArrayList<>();
			return universities;
		});
	}
	
	private void setMockOutputStudentReview() {
		when(studentReviewDao.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(UNI_ID)) {
				StudentReview sr = new StudentReview();
				Student student = new Student();
				Tutor tutor = new Tutor();
				student.setUsername(STUDENT_USERNAME);
				student.setName(STUDENT_NAME);
				student.setPassword(STUDENT_PASS);
				tutor.setUsername(TUT_USERNAME);
				tutor.setName(TUT_NAME);
				tutor.setPassword(Tutor_PASS);
				tutor.setHourlyRate(TUT_RATE);
				sr.setAuthor(tutor);
				sr.setId(UNI_ID);
				sr.setReview("good");
				sr.setReviewee(student);
				
				return sr;
			} else {
			return null;
			}
		});
	}
	
	
	//Mock Tests Tutors
	private void setMockOutputTutor() {
		when(tutorDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Tutor> tutors = new ArrayList<>();
			Tutor tutor = new Tutor();
			tutor.setName(TUT_NAME);
			tutor.setUsername(TUT_USERNAME);
			tutors.add(tutor);
			return tutors;
		});	
	}
	
	private void setMockOutputTutorEmpty() {
		when(tutorDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Tutor> tutors = new ArrayList<>();
			return tutors;
		});
	}
	
	private void setMockOutputUniversityInvalidName() {
		when(universityDao.findUniversityByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			return null;
		});
	}
	
	private void setMockOutputCourse() {
		when(courseDao.findByCourseCode(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSE_CODE)) {
				University uni = new University();
				uni.setName(UNI_NAME);
				uni.setId(UNI_ID);
				Course course = new Course();
				course.setCourseCode(COURSE_CODE);
				course.setSubject(COURSE_SUB);
				course.setUniversity(uni);
				return course;
			} else {
				return null;
			}
		});
	}
	
	
	
	// mock output for course
		private void setMockOutputAllCourse() {
			when(courseDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
				// create a university
				University uni = new University();
				uni.setName(UNI_NAME);
				uni.setId(UNI_ID);
				
				// create a course
				List<Course> courses = new ArrayList<>();
				Course c = new Course();
				c.setCourseCode(COURSE_CODE);
				c.setSubject(COURSE_SUB);
				c.setUniversity(uni);
				courses.add(c);
				return courses;
			});
		}
		
		// mock output for course
		private void setMockOutputCourseNull() {
			when(courseDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
				return null;
			});
		}
		
		// mock output for an empty course list
		private void setMockOutputCourseEmpty() {
			when(courseDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
				// create a course list 
				List<Course> cs = new ArrayList<>();
				return cs;
			});
		}
		
		// mock output for a course without a university
		private void setMockOutputCourseNoUni() {
			when(courseDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
				// create a course
				List<Course> courses = new ArrayList<>();
				Course c = new Course();
				c.setCourseCode(COURSE_CODE);
				c.setSubject(COURSE_SUB);
				courses.add(c);
				return courses;
			});
		}
	
	
	@Test
	public void testCreateValidStudent() {
	Student s= null;
		try {
			s=service.createStudent(STUDENT_USERNAME,STUDENT_PASS, STUDENT_NAME);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//List<Student> allStudents = service.getAllStudents();

		//assertEquals(1, allStudents.size());
		assertEquals(STUDENT_USERNAME,s.getUsername());
		assertEquals(STUDENT_PASS,s.getPassword());
		assertEquals(STUDENT_NAME,s.getName());
	}
	
	@Test
	public void testGetStudent() {
		Student s = new Student();
		try {
			s = service.getStudent(STUDENT_USERNAME);
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(STUDENT_USERNAME, s.getUsername());
		assertEquals(STUDENT_NAME, s.getName());
		assertEquals(STUDENT_PASS,s.getPassword());
	}
	
	@Test
	public void testCreateStudentNullUsername() {

		String error = null;

		try {
			service.createStudent(null,STUDENT_PASS, STUDENT_NAME);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Username can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentEmptyUsername() {

		String error = null;

		try {
			service.createStudent("",STUDENT_PASS, STUDENT_NAME);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Username can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}

	@Test
	public void testCreateStudentNullPassword() {

		String error = null;

		try {
			service.createStudent(STUDENT_USERNAME, null, STUDENT_NAME);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Password can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentEmptyPassword() {

		String error = null;

		try {
			service.createStudent(STUDENT_USERNAME, "", STUDENT_NAME);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Password can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentNullName() {

		String error = null;

		try {
			service.createStudent(STUDENT_USERNAME, STUDENT_PASS, null);
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Name can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testCreateStudentEmptyName() {

		String error = null;

		try {
			service.createStudent(STUDENT_USERNAME, STUDENT_PASS, "");
		} catch (IllegalArgumentException e) {
			
			error = e.getMessage();
		}

		assertEquals("Name can't be empty.", error.trim());
		List<Student> allStudents = service.getAllStudents();

		assertEquals(0, allStudents.size());
	}
	
	@Test
	public void testAllTutors() {
		List<Tutor> tutors = new ArrayList<>();
		Tutor tutor = null;
		
		try {
			tutor = service.createTutor(TUT_NAME, TUT_USERNAME, TUT_PWD, TUT_RATE);
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		
		tutors.add(tutor);
		assertEquals(1, tutors.size());
		assertEquals(TUT_NAME, tutors.get(0).getName());
		assertEquals(TUT_USERNAME, tutors.get(0).getUsername());
		assertEquals(TUT_PWD, tutors.get(0).getPassword());
		assertEquals(TUT_RATE, tutors.get(0).getHourlyRate());
	
	}
	
	// check that the service can retrieve all universities properly
		@Test
		public void getAllUniversities() {
			List<University> uniList = new ArrayList<>();
			
			// get all universities
			try {
				uniList = service.getAllUniversities();
			} catch(IllegalArgumentException e) { fail();}
			
			// check that only 1 and its the right one
			assertEquals(1, uniList.size());
			University u = uniList.get(0);
			assertEquals(UNI_NAME, u.getName());
			assertEquals(UNI_ID, u.getId());
		}
		
		// check for no universities created
		// test coverage: 100%
		@Test
		public void getAllUniversitiesEmpty() {
			List<University> uniList = new ArrayList<>();
			
			// run the correct mock output
			setMockOutputUniversityEmpty();
			
			// get all universities
			try {
				uniList = service.getAllUniversities();
			} catch(IllegalArgumentException e) { fail();}
			
			assertEquals(0, uniList.size());
		}
		
		@Test
		public void getUniversityByInvalidName() {
			setMockOutputUniversityInvalidName();
			University u = new University();
			String error = "";
			try {
				u = service.getUniversity(NONEXISTING_SCHOOLNAME);
			}catch(IllegalArgumentException e) { 
				error = e.getMessage();
			}
			assertEquals("University does not exist.",error);
			
		}
	
	// check that we can get all the courses
		@Test
		public void getAllCourses() {
			List<Course> courseList = new ArrayList<>();
			
			// get all universities
			try {
				courseList = service.getAllCourses();
			} catch(IllegalArgumentException e) { fail();}
			
			// check that only 1 and its the right one
			assertEquals(1, courseList.size());
			Course c = courseList.get(0);
			assertEquals(COURSE_CODE, c.getCourseCode());
			assertEquals(COURSE_SUB, c.getSubject());
		}
		
		// check that we can get all the courses
		@Test
		public void getAllCoursesEmpty() {
			List<Course> courseList = new ArrayList<>();
			
			// run the correct mock ouptut
			setMockOutputCourseEmpty();
			
			// get all universities
			try {
				courseList = service.getAllCourses();
			} catch(IllegalArgumentException e) { fail();}
			
			// check that only 1 and its the right one
			assertEquals(0, courseList.size());
		}
		
		// check if we can get course using course code
		@Test
		public void getCourseByCourseCode() {
			Course c = new Course();
			try {
				c = service.getCourse(COURSE_CODE);
			} catch(IllegalArgumentException e){ 
				//System.out.println(e.getMessage());
				fail(); 
			}
			
			assertEquals(COURSE_CODE, c.getCourseCode());
			assertEquals(COURSE_SUB, c.getSubject());
			assertEquals(UNI_ID, c.getUniversity().getId());
			
		}
		
		@Test
		public void getCourseByCourseCodeNull() {
			
			String error = "";
			Course c = new Course();
			try {
				c = service.getCourse(null);
			} catch(IllegalArgumentException e){ 
				error = e.getMessage();
				//fail(); 
			}
			String errorMessage = "Course can't be empty.";
			
			assertEquals(error, errorMessage);
		
		}
		
		@Test
		public void getCourseByCourseCodeEmpty() {
			
			String error = "";
			Course c = new Course();
			try {
				c = service.getCourse("");
			} catch(IllegalArgumentException e){ 
				error = e.getMessage();
				//fail(); 
			}
			String errorMessage = "Course can't be empty.";
			
			assertEquals(error, errorMessage);
		}
		
		@Test
		public void getCourseByCourseCodeNoUni() {
			setMockOutputCourseNoUni();
			List<Course> courses = new ArrayList<>();
			try {
				courses = service.getAllCourses();
			}catch(IllegalArgumentException e){
				fail();
			}
			assertEquals(1,courses.size());
		}

}
