package com.learn.cruddemo.dao;

import com.learn.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
    List<Student> findByFirstNameOrLastName(String theFirstName, String theLastName);
    List<Student> findByEmailLike(String theEmail);

    void update(Student theStudent);
    Integer updateAllLastName(String theLastName);

    void delete(Integer id);
    int deleteAll();
    int deleteByLastName(String theLastName);

}
