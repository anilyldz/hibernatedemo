package com.anily.hibernatedemo;

import com.anily.hibernatedemo.dao.AppDAO;
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
			createInstructor(appDAO);
		};
	}

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Anil", "Yildiz", "anilyildiz@mail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube.com/anilyildiz", "fitness");
        instructor.setInstructorDetailId(instructorDetail);
        System.out.println("Saving instructor: " + instructor);
        appDAO.save(instructor);
        System.out.println("Saving completed");
		findInstructor(appDAO, instructor);
		deleteInstructor(appDAO, instructor);
    }

	private void findInstructor(AppDAO appDAO, Instructor instructor) {
		System.out.println("Finding instructor: " + appDAO.findById(instructor.getId()));
	}

	private void deleteInstructor(AppDAO appDAO, Instructor instructor) {
		System.out.println("Deleting instructor: " + instructor);
		appDAO.deleteInstructorById(instructor.getId());
	}

}
