package com.hustlecrafts.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {			
			//create a objects
			Instructor instructor = new Instructor("Prashant", "Balotra", "balotra@gmail.com");
			
			InstructorDetail detail = new InstructorDetail("curiousprashant@youtube.com", "Luv to code");
			
			//associate the objects
			instructor.setInstructorDetail(detail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			//Note: this will also save the detail object
			//because of CascadeType.ALL
			System.out.println("Saving instructor" + instructor);
			session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			
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
