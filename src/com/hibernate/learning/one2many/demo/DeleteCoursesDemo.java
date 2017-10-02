package com.hibernate.learning.one2many.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.one2many.entity.Course;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class DeleteCoursesDemo {

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
			
			List<Course> courses = tempInstructor.getCourses();
			System.out.println("List of COurses: "+ courses);
			
			for(Course tempCourse: courses)
			{
				if(tempCourse.getTitle().equals("HOckey Course"))
				{
					System.out.println(tempCourse);
					//tempCourse.setInstructor(null);
					session.delete(tempCourse);
					break;
				}
			}
			
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
