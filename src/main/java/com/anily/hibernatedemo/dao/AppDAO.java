package com.anily.hibernatedemo.dao;

import com.anily.hibernatedemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(String id);

    void deleteInstructorById(String id);

}
