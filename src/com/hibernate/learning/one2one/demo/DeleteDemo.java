package com.hibernate.learning.one2one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		
		// create session & start a transaction
		Session session = factory.getCurrentSession();
		
		
		try
		{
			int instructorId = 2;
			
			//start the transaction
			session.beginTransaction();
			
			// retrieve the instructor
			System.out.println("Getting the instructor with id: "+ instructorId);
			Instructor tempInstructor = session.get(Instructor.class, instructorId);
			System.out.println(tempInstructor);
			
			
			// delete the instructor
			//note: This will also delete the instructor detail object due to cascading effect
			if(tempInstructor != null)
			{
				System.out.println("Deleting: "+tempInstructor);
				session.delete(tempInstructor);
			}
			
	
			// commit the instructor object
			session.getTransaction().commit();
			
			
			
			System.out.println("Done!");
			
			 
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			factory.close();
		}
		
		
	}

}
