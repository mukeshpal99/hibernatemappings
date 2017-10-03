package com.hibernate.learning.fetchType;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.entity.Course;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class BreakLazyFetchSoln1 {

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
			System.out.println("Instructor: "+tempInstructor);

			//calling the get coursed method while session is open
			// this will not throw error on line 51, calling get courses after session is closed
			List<Course> courses = tempInstructor.getCourses();
			System.out.println("List of COurses: "+ courses);

			// commit the instructor object
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			courses = tempInstructor.getCourses();
			System.out.println("List of COurses: "+ courses);
			
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
