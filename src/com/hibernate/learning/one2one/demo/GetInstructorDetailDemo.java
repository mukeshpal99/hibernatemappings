package com.hibernate.learning.one2one.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
			int instructorDetailId = 5;
			
			//start the transaction
			session.beginTransaction();
			
			// retrieve the instructor detail
			System.out.println("Getting the instructor with id: "+ instructorDetailId);
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, instructorDetailId);
			Instructor tempInstructor = null;
			if(tempInstructorDetail != null)
			{
				System.out.println("Instructor detail: "+tempInstructorDetail);
				
				// retrieve the associated instructor
				 tempInstructor = tempInstructorDetail.getInstructor();
				System.out.println("Instructor: "+tempInstructor);
				
			}
	
			// commit the instructor object
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			 
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			session.close();
			factory.close();
		}
		
		
	}

}
