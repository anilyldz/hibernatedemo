package com.anily.hibernatedemo;

import com.anily.hibernatedemo.dao.AppDAO;
import com.anily.hibernatedemo.entity.Course;
import com.anily.hibernatedemo.entity.Instructor;
import com.anily.hibernatedemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			createInstructorWithCourses(appDAO);
		};
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

	private void deleteInstructor(AppDAO appDAO, Instructor instructor) {
		System.out.println("Deleting instructor : " + instructor);
		appDAO.deleteInstructorById(instructor.getId());
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

}
