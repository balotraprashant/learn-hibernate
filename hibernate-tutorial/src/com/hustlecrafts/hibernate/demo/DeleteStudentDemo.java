package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.jdbc.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 2;
			
			//start a transaction
			session.beginTransaction();
									
			Student retrievedStudent = session.get(Student.class, studentId);
			
			System.out.println("Deleting student!!");
			
			//delete without creating Query
			//session.delete(retrievedStudent);
			
			//delete with creating Query
			session.createQuery("delete from Student where id=" + studentId)
			.executeUpdate();
			
			session.getTransaction().commit();
			
//			
//			session.createQuery("update Student set lastName='Sharma'")
//			.executeUpdate();
//			
//			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		finally {
			factory.close();
		}
	}

}
