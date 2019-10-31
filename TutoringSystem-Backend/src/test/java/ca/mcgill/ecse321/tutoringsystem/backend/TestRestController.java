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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
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
	
	
	@InjectMocks
	private TutoringSystemService service;
	
	private static final String STUDENT_USERNAME = "TestStudent";
	private static final String NONEXISTING_USERNAME = "NotAStudent";
	private static final String STUDENT_NAME ="TestName";
	private static final String NONEXISTING_NAME ="NotAName";
	private static final String STUDENT_PASS ="TestPass";
	private static final String NONEXISTING_PASS ="NotAPass";
	private static final String STUDENT_SCHOOLNAME ="TestPass";
	private static final String NONEXISTING_SCHOOLNAME ="NotAPass";

	private static final String UNI_NAME = "TestUniName";
	private static final int UNI_ID = 1;

	private static final String COURSE_CODE = "TestCode";
	private static final String COURSE_SUB ="TestSubject";
	
	
	@Before
	public void setMockOutput() {
		setMockOutputStudent();
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
