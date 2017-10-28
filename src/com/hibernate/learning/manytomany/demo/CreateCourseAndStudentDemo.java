package com.hibernate.learning.manytomany.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.learning.manytomany.entity.Student;
import com.hibernate.learning.one2many.bidirectional.entity.Course;
import com.hibernate.learning.one2many.unidirectional.entity.Review;
import com.hibernate.learning.one2one.entity.Instructor;
import com.hibernate.learning.one2one.entity.InstructorDetail;

public class CreateCourseAndStudentDemo {
	
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
			//create a course
			Course tempCourse = new Course("The New sql course");
			session.save(tempCourse);
			
			//create some students
			Student student1 = new Student("user1", "lname", "abc@gmail.com");
			Student student2 = new Student("user2", "lname", "abc@gmail.com");
			
			//asociate course with student
			//tempCourse.addStudent(student1);
			//tempCourse.addStudent(student2);
			
			student1.addCourse(tempCourse);
			student2.addCourse(tempCourse);
			
			// save transaction
			session.save(student1);
			session.save(student2);

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
