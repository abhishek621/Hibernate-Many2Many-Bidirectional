package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman - How to score one million points");

			// save a course
			System.out.println("\nSaving the course");
			session.save(tempCourse);
			System.out.println("Saved the course : " + tempCourse);

			// create the students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			// save the students
			System.out.println("\nSaving the students");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved the students : " + tempCourse.getStudents());

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}

}
