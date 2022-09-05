package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.jdbc.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 3;
			
			//start a transaction
			session.beginTransaction();
									
			Student retrievedStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student!!");
			retrievedStudent.setFirstName("Astitva");
			retrievedStudent.setEmail("astitvabalotra@gmail.com");
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set lastName='Sharma'")
			.executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		finally {
			factory.close();
		}
	}

}
