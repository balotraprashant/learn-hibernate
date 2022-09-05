package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetail {

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
			int instructorDetailId = 3;
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			InstructorDetail detail = session.get(InstructorDetail.class, instructorDetailId);
			
			//print the instructor detail
			System.out.println(detail);
			
			//print the associated instructor
			System.out.println(detail.getInstructor());
			
			//remove the associated object reference
			//break bi-directional link
			detail.getInstructor().setInstructorDetail(null);
			
			System.out.println("Deleting instructor detail");
			//delete instructor detail
			session.delete(detail);
			
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
