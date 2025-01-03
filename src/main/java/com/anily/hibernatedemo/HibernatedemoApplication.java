package com.anily.hibernatedemo;

import com.anily.hibernatedemo.dao.AppDAO;
import com.anily.hibernatedemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			deleteSudent(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//findStudentAndCourses(appDAO);
			//findCourseAndStudents(appDAO);
			//createCoursesAndStudents(appDAO);
			//createInstructor(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);
			//createCourseAndReviews(appDAO);
			//findCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteSudent(AppDAO appDAO) {
		String id = "1";
		System.out.println("Deleting Student id: " + id);
		appDAO.deleteStudent(id);
	}


	private void addMoreCoursesForStudent(AppDAO appDAO) {
		String id = "2";
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		Course course1 = new Course("Rubik's Cube - How to speed it");
		Course course2 = new Course("Atari 2600 - Game Development");
		student.addCourse(course1);
		student.addCourse(course2);
		System.out.println("Updating Student and associated Course: " + student + " " + student.getCourses());
		appDAO.updateStudent(student);
	}

	public void findStudentAndCourses(AppDAO appDAO) {
		String id = "1";
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Finding Course and associated Students: " + tempStudent + " " + tempStudent.getCourses());
	}


	public void findCourseAndStudents(AppDAO appDAO) {
		String id = "10";
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(id);
		System.out.println("Finding Course and associated Students: " + tempCourse + " " + tempCourse.getStudents());
	}


	public void createCoursesAndStudents(AppDAO appDAO) {
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		Student student = new Student("John", "Doe", "john@doe.com");
		Student student2 = new Student("Mary", "White", "mary@white.com");

		tempCourse.addStudent(student);
		tempCourse.addStudent(student2);

		System.out.println("Saving course and associated students: " + tempCourse + " " + tempCourse.getStudents());
		appDAO.saveCourse(tempCourse);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		String id = "11";
		appDAO.deleteCourseById(id);
	}


	private void findCourseAndReviews(AppDAO appDAO) {
		String id = "10";
		System.out.println("Finding course id : " + id);
		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println("Course : " + course.toString());
		System.out.println("Reviews : " + course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Spring Boot");
		course.addReview(new Review("Great course, loved it!!"));
		course.addReview(new Review("Cool course, job well done!!"));
		course.addReview(new Review("What a dumb course, you are suck"));
		System.out.println("Saving course");
		appDAO.saveCourse(course);
		System.out.println("Saving course completed");
	}

	private void deleteCourse(AppDAO appDAO) {
		String id = "10";
		System.out.println("Finding course id : " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}


	private void updateCourse(AppDAO appDAO) {
		String id = "10";
		System.out.println("Finding course id : " + id);
		Course course = appDAO.findCourseById(id);
		course.setTitle("Fitness");
		appDAO.updateCourse(course);
		System.out.println("Update completed");
	}

	private void updateInstructor(AppDAO appDAO) {
		String id = "1";
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		instructor.setLastName("TEST");
		appDAO.updateInstructor(instructor);
		System.out.println("Update completed");
	}


	private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Anil", "Yildiz", "anilyildiz@mail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube.com/anilyildiz", "fitness");
        instructor.setInstructorDetail(instructorDetail);
		instructorDetail.setInstructor(instructor);
        System.out.println("Saving instructor: " + instructor);
        appDAO.save(instructor);
        System.out.println("Saving completed");
		findInstructor(appDAO, instructor);
		findInstructorDetail(appDAO, instructorDetail);
		//deleteInstructor(appDAO, instructor);
		//deleteInstructorDetail(appDAO, instructorDetail);
    }

	private void findInstructor(AppDAO appDAO, Instructor instructor) {
		System.out.println("Finding instructor: " + appDAO.findInstructorById(instructor.getId()));
	}

	private void deleteInstructor(AppDAO appDAO) {
		String id = "1";
		System.out.println("Deleting instructor id : " + id);
		appDAO.deleteInstructorById(id);
	}

	private void findInstructorDetail(AppDAO appDAO, InstructorDetail instructorDetail){
		System.out.println("Finding Instructor Detail : " + appDAO.findInstructorDetailById(instructorDetail.getId()));
	}

	private void deleteInstructorDetail(AppDAO appDAO, InstructorDetail instructorDetail) {
		System.out.println("Deleting instructor Detail : " + instructorDetail);
		appDAO.deleteInstructorDetailById(instructorDetail.getId());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Anil", "Yildiz", "anilyildiz@mail.com");
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/anilyildiz", "fitness");

		instructor.setInstructorDetail(instructorDetail);
		instructorDetail.setInstructor(instructor);

		Course course1 = new Course("Guitar");
		Course course2 = new Course("Pinball");

		instructor.add(course1);
		instructor.add(course2);

		//This will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + instructor);
		System.out.println("Courses : " + instructor.getCourses());
		appDAO.save(instructor);
		System.out.println("Saving completed");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		String id = "1";
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor : " + instructor);
		System.out.println("the associated courses : " + instructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		String id = "1";
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor : " + instructor);
		System.out.println("Finding courses for instructor id: "+ id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("Courses of Instructor"+ instructor.getCourses());
	}
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		String id = "1";
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor : " + instructor);
		System.out.println("Courses of Instructor"+ instructor.getCourses());
	}
}
