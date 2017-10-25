package com.hibernate.learning.one2many.bidirectional.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			//create the instructor object
			System.out.println("creating instructor object: ");
			Instructor tempInstructor = new Instructor("Tutor1", "lastname", "test@test.com");
			
			//create the instructor detail object
			System.out.println("creating instructor object: ");
			InstructorDetail tempInstructorDetail = new InstructorDetail("Youtube.com/channel1", "hobby1");
			
			//associate the instructor detail object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start the transaction
			session.beginTransaction();
			
			//save the instructor object
			//note: This will also save the instructor detail object due to cascading effect
			System.out.println("Saving the instructor");
			System.out.println(tempInstructor);
			session.save(tempInstructor);
			
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
