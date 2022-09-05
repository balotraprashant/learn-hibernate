package com.hustlecrafts.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.jdbc.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save java object
			System.out.println("Creating a new student object");
			
			String dateOfBirth = "09/02/2001";
			
			//create a student object
			Student student = new Student("Sudhanshu", "Balotra", "sudhanshu1126@gmail.com", DateUtils.parseDate(dateOfBirth));
			
			//start a transaction
			session.beginTransaction();
			
			System.out.println(student);
			
			//save the student object
			session.save(student); 
			
			//commit transaction
			session.getTransaction().commit();
			
			//retrieve
			System.out.println("Generated student id is: " + student.getId());
			
			Session session2 = factory.getCurrentSession();
			
			session2.beginTransaction();
			
			Student retrievedStudent = session2.get(Student.class, student.getId());
			
			System.out.println(retrievedStudent);
			
			System.out.println("Done!!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
