package com.anily.hibernatedemo.dao;

import com.anily.hibernatedemo.entity.Course;
import com.anily.hibernatedemo.entity.Instructor;
import com.anily.hibernatedemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(String id);

    void deleteInstructorById(String id);

    InstructorDetail findInstructorDetailById(String id);

    void deleteInstructorDetailById(String id);

    List<Course> findCoursesByInstructorId(String id);

}
