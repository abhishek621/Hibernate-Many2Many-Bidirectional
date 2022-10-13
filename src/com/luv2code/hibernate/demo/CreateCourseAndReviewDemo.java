package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {

		// create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		// create Session

		Session session = factory.getCurrentSession();

		// use the session object to save the java object

		try {

			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman - How to score one million points");

			// add some review
			tempCourse.addReview(new Review("Great Course... Loved it!"));
			tempCourse.addReview(new Review("Cool Course... Job well done!"));
			tempCourse.addReview(new Review("What a dumb Course... You are an idiot!"));

			// save the course... and leverage the cascade ALL
			System.out.println("Saving the Course");
			System.out.println(tempCourse);
			System.out.println("Reviews : " + tempCourse.getReviews());

			session.save(tempCourse);

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
