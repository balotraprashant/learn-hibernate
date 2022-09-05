package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hustlecrafts.hibernate.entity.Course;
import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;

public class GetInstructorCoursesLazyHQLJOIN {

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
			
			//option 2: Hibernate query with HQL
			//here we solved connection closed issue in case of Lazy loading
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id=:instructorId",
							Instructor.class);
			
			//set parameter on query
			query.setParameter("instructorId", instructorId);
			
			//execute the query and get the instructor detail object
			Instructor instructor = query.getSingleResult();
			
			System.out.println("Instructor : " + instructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			//closing connection
			session.close();
			
			System.out.println("\nConnection is closed now!!\n");
						
			//print the associated courses
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
