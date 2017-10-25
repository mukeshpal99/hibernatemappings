package com.hibernate.learning.one2many.unidirectional.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2many.unidirectional.entity.Review;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class CreateCourseAndReviewDemo {
	
	
	public static void main(String[] args)
	{
		//create a session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
							
									
		//create session and start transaction
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		
		// create a course 
		Course tempCourse = new Course("Social Science");
		
		// create some reviews
		Review newReview1 = new Review("The bese course ever1");
		Review newReview2 = new Review("The bese course ever2");
		
		// add the course to reviews
		tempCourse.addReview(newReview1);
		tempCourse.addReview(newReview2);
		
		// save the session
		session.save(tempCourse);
		
		// commit the transaction
		session.getTransaction().commit();
		
		System.out.println("!Done");
	}
	

}
