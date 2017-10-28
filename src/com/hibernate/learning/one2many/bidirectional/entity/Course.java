package com.hibernate.learning.one2many.bidirectional.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hibernate.learning.manytomany.entity.Student;
import com.hibernate.learning.one2many.unidirectional.entity.Review;
import com.hibernate.learning.one2one.entity.Instructor;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade={CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	


	@OneToMany(cascade=CascadeType.ALL,
				fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade={CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH}
				)
	@JoinTable(name="course_student",
				joinColumns=@JoinColumn(name="course_id"),
				inverseJoinColumns=@JoinColumn(name="student_id")
				)
	private List<Student> students;
	
	public Course(){}


	public Course(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	public List<Student> getSudents() {
		return students;
	}


	public void setSudents(List<Student> sudents) {
		this.students = sudents;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
	
	
	public void addReview(Review review){
		
		if(this.reviews == null) this.reviews = new ArrayList<Review>();
		reviews.add(review);
		
	}

	
	public void addStudent(Student tempStudent){
		if(students == null) this.students = new ArrayList<Student>();
		students.add(tempStudent);
	}
}
