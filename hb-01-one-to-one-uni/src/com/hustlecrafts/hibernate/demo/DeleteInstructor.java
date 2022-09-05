package com.hustlecrafts.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;

public class DeleteInstructor {

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
			int instructorId = 1;
			//start a transaction
			session.beginTransaction();
			
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			System.out.println("Found Instructor :" + instructor);
			
			if(instructor!=null) {
				System.out.println("Deleting instructor");
				
				//delete the instructor
				//Note: this will also delete the instructor_detail object
				//because of CascadeType.ALL
				
				session.delete(instructor);
			}
			
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
