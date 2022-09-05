package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Course;
import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;

public class GetInstructorCoursesLazy {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			int instructorId = 1;
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			System.out.println("Courses : " + instructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
			
			//closing connection
			session.close();
			
			System.out.println("\nConnection is closed now!!\n");
			
			//print the instructor detail
			System.out.println("Instructor : " + instructor);
			
			//print the associated instructor
			System.out.println("Courses : " + instructor.getCourses());
			
			System.out.println("Done!!");
		}
		catch (Exception exc) {
            exc.printStackTrace();
        }
		finally {
			factory.close();
		}
	}

}
