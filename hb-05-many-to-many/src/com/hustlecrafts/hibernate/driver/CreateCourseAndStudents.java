package com.hustlecrafts.hibernate.driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hustlecrafts.hibernate.entity.Course;
import com.hustlecrafts.hibernate.entity.Instructor;
import com.hustlecrafts.hibernate.entity.InstructorDetail;
import com.hustlecrafts.hibernate.entity.Review;
import com.hustlecrafts.hibernate.entity.Student;

public class CreateCourseAndStudents {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//create objects
			Course course = new Course("Android Development");
			
			//save the course
			System.out.println("Saving Course ...");
			session.save(course);
			
			//add some students to course
			
			Student std1 = new Student("Prashant", "Balotra", "balotra.prashant@gmail.com");
			Student std2 = new Student("John", "Doe", "john@gmail.com");
			Student std3 = new Student("Dhanu", "Balotra", "dhanu@gmail.com");
			
			course.addStudent(std1);
			course.addStudent(std2);
			course.addStudent(std3);
			
			//save the students
			System.out.println("Saving Students ...");
			session.save(std1);
			session.save(std2);
			session.save(std3);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saved students : " + course.getStudents());
			
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
