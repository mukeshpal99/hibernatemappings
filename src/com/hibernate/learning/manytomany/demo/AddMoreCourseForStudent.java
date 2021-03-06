package com.hibernate.learning.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.manytomany.entity.Student;
import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2many.unidirectional.entity.Review;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class AddMoreCourseForStudent {
	
	
	public static void main(String[] args)
	{
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		
		// create session an dbegin trnsaction
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		try
		{
			//get the student where to add course
			int stID = 2;
			Student tempStudent = session.get(Student.class, stID);
			
			//create a course
			Course tempCourse = new Course("The New hibernate course");
			
			
			//asociate course with student
			tempCourse.addStudent(tempStudent);
			
			
			// save transaction
			session.save(tempCourse);

			//commit transaction
			session.getTransaction().commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			session.close();
			factory.close();
		}
		
		
	}
}
