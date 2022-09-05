package com.hustlecrafts.hibernate.driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Course;
import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;
import com.hustlecrafts.hibernate.entity.Review;

public class CreateCourseAndReviews {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//create objects
			Course course = new Course("Android Development");
			
			//add reviews to course
			
			course.addReview(new Review("Great course, loved it..!"));
			course.addReview(new Review("Great job..."));
			course.addReview(new Review("Best course ever...!"));
			
			
			//save the courses
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		catch (Exception exc) {
            exc.printStackTrace();
        }
		finally {
			session.close();
			factory.close();
		}
	}

}
