package com.hibernate.learning.one2many.bidirectional.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		
		// create session & start a transaction
		Session session = factory.getCurrentSession();
	
		
		
		try
		{
			
			//start the transaction
			session.beginTransaction();
			
			// get the instructor from DB
			int instructorId = 1;
			Instructor tempInstructor= session.get(Instructor.class, instructorId);
			
			//create some courses
			Course tempCourse1 = new Course("Java Course");
			tempCourse1.setInstructor(tempInstructor);
			
			Course tempCourse2 = new Course("HOckey Course");
			tempCourse2.setInstructor(tempInstructor);
			//add courses to instructor
			
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// commit the instructor object
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			 
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			// add a clean up code
			session.close();
			factory.close();
		}
		
		
	}

}
