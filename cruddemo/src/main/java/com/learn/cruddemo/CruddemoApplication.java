package com.learn.cruddemo;

import com.learn.cruddemo.dao.StudentDAO;
import com.learn.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudent(studentDAO);

			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//queryForStudentsByFirstNameOrLastName(studentDAO);
			//queryForStudentsByEmailLike(studentDAO);

            //updateStudent(studentDAO);
			//updateStudentsLastName(studentDAO);

			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
			//deleteByLastName(studentDAO);
		};
	}

	private void deleteByLastName(StudentDAO studentDAO) {
		String theLastName = "Ure";
		System.out.println("Deleting all students with last name: " + theLastName);

		int numRowsDeleted = studentDAO.deleteByLastName(theLastName);
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudentsLastName(StudentDAO studentDAO) {
		Integer numRowsUpdated = studentDAO.updateAllLastName("Samuelsen");
		System.out.println("Number of updated students: " + numRowsUpdated);

		List<Student> students = studentDAO.findAll();
		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
        // Retrieve student based on id: primary key
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

        // Change first name to "Mari"
        System.out.println("Updating student ...");
        myStudent.setFirstName("Mari");

        // Update the student
        studentDAO.update(myStudent);

        // Display the updated student
        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByEmailLike(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByEmailLike("%luv2code.com");

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudentsByFirstNameOrLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByFirstNameOrLastName("Max", "Doe");

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// Display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Max", "Richter", "max@learn.com");

		// Save the student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// Retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// Display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// Create multiple students
		System.out.println("Creating 3 student object ...");
		Student tempStudent1 = new Student("Twim", "Shadow", "twim@learn.com");
		Student tempStudent2 = new Student("Midge", "Ure", "midge@learn.com");
		Student tempStudent3 = new Student("Tame", "Impala", "tame@learn.com");

		// Save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// Create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// Save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

}
