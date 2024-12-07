package com.anily.hibernatedemo.dao;

import com.anily.hibernatedemo.entity.Course;
import com.anily.hibernatedemo.entity.Instructor;
import com.anily.hibernatedemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(String id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(String id) {
        Instructor tempInstructor = findInstructorById(id);
        List<Course> courses = tempInstructor.getCourses();
        //Break association of all courses for the instructor
        for (Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(String id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(String id) {
        InstructorDetail tempInstructorDetail = findInstructorDetailById(id);
        //remove the associated object reference
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(String instructorId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id=:id", Course.class);
        query.setParameter("id", instructorId);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(String id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail "
                + "where i.id = :id", Instructor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(String id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(String id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(String id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                +"where c.id = :data", Course.class);

        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(String id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", id);
        return query.getSingleResult();
    }
}
