package com.hustlecrafts.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.jdbc.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> students = session.createQuery("from Student").list();
			
			displayStudents(students); 
			
			//query students
			List<Student> students1 = session.createQuery("from Student s where s.firstName='Prashant'").getResultList();
			
			//display the students
			System.out.println("\n\nDisplaying the students where first name is Prashant");
			displayStudents(students1);
			
			//query students
			List<Student> students2 = session.createQuery("from Student s where s.firstName='Prashant' OR s.firstName='Sudhanshu'").getResultList();
			
			//display the students
			System.out.println("\n\nDisplaying the students where first name is Prashant or Sudhanshu");
			displayStudents(students2); 
			
			//query students
			List<Student> students3 = session.createQuery("from Student s where s.email LIKE 'balotra%'").getResultList();
			
			//display the students
			System.out.println("\n\nDisplaying the students where email starts with balotra");
			displayStudents(students3); 
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for(Student student: students) System.out.println(student);
	}

}
