package com.hibernate.learning.one2many.unidirectional.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2many.unidirectional.entity.Review;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class GetReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		
		// create session & start a transaction
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		try
		{
			
			// get the course from DB
			int courseId = 10;
			Course tempCourse= session.get(Course.class, courseId);
			
			List<Review> reviews = tempCourse.getReviews();
			System.out.println("List of COurses: "+ reviews);
			
			
			
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
