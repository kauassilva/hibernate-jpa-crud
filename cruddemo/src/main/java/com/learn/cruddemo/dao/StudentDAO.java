package com.learn.cruddemo.dao;

import com.learn.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);
    Student findById(Integer id);

}
