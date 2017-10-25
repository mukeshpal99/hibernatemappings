package com.hibernate.learning.fetchType;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class BreakLazyFetchSoln2 {

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
			
			// create a HQL query to fetch instructor and courses
			Query<Instructor> query = session.createQuery("Select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId", Instructor.class);
			
			// set parameter for query
			query.setParameter("theInstructorId", instructorId);
			
			// execute the query
			Instructor tempInstructor = query.getSingleResult();

			// commit the instructor object
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("Session is closed");
			
			List<Course>courses = tempInstructor.getCourses();
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
