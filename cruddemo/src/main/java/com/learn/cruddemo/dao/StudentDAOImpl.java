package com.learn.cruddemo.dao;

import com.learn.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define field for entity manager
    private EntityManager entityManager;

    // Inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",
                Student.class);
        // Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);
        // Set query parameters
        theQuery.setParameter("theData", theLastName);
        // Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstNameOrLastName(String theFirstName, String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE firstName=:theFirstName OR lastName=:theLastName",
                Student.class);
        theQuery.setParameter("theFirstName", theFirstName);
        theQuery.setParameter("theLastName", theLastName);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByEmailLike(String theEmail) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE email LIKE:theEmail", Student.class);
        theQuery.setParameter("theEmail", theEmail);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public Integer updateAllLastName(String theLastName) {
        return entityManager.createQuery("UPDATE Student SET lastName=:theLastName")
                .setParameter("theLastName", theLastName)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // Delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }

    @Override
    @Transactional
    public int deleteByLastName(String theLastName) {
        return entityManager.createQuery("DELETE FROM Student WHERE lastName=:theLastName")
                .setParameter("theLastName", theLastName)
                .executeUpdate();
    }

}
