/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.GradeCardDaoInterface;
import com.crs.flipkart.dao.GradeCardDaoOperation;
import com.crs.flipkart.dao.StudentDaoInterface;

/**
 * @author HP
 *
 */
public class StudentImplementation implements StudentInterface{
	
	private static StudentImplementation instance = null;
	private CourseInterface courseImplementation = CourseImplementation.getInstance();
	private GradeCardDaoInterface gradeCardOperation = GradeCardDaoOperation.getInstance();
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();
	private StudentDaoInterface studentDaoOperation = StudentDaoOperation.getInstance();

	public StudentImplementation(){}

	public static StudentImplementation getInstance(){
		if(instance==null){
			synchronized (StudentImplementation.class){
				instance = new StudentImplementation();
			}
		}
		return instance;
	}

	private StudentDaoInterface studentDaoImplementation = StudentDaoOperation.getInstance();
	
	
	public void addStudentdata(Student student) {
		StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
		if(studentDaoOperation.addStudentData(student)) {
			System.out.println("student is added");
		}
	}

	public Student viewStudentDetails(int studentId)
	{
		return studentDaoImplementation.viewStudentDetails(studentId);
	}
	public ArrayList<Student> viewStudentData()
	{
		return studentDaoImplementation.viewAllStudents();
	}
	
	@Override
	public void displayCourseCatalog() {
		AdminInterface adminImplementation = new AdminImplementation();
		adminImplementation.viewAllCourses();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGradeCard(int studentId) {
		// TODO Auto-generated method stub
		
		StudentRegisteredCourses studentRegisteredCourses = courseDaoImplementation.getStudentRegisteredCourses(studentId);
		
		GradeCard gradeCard = gradeCardOperation.getGradeCard(studentId);
		int grade1 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId1());
		int grade2 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId2());
		int grade3 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId3());
		int grade4 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId4());
		
		if(gradeCard.isPublished())
		{
			System.out.println("Student Id: "+ gradeCard.getStudentId());
			System.out.println("Semester: "+ gradeCard.getSemester());
			System.out.println("SGPA: "+ gradeCard.getSgpa());
			System.out.println("Course-1: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId1()).getName() +"\t: Grade:" + grade1);
			System.out.println("Course-2: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId2()).getName()+"\t: Grade:" + grade2);
			System.out.println("Course-3: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId3()).getName() +"\t: Grade:" + grade3);
			System.out.println("Course-4: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId4()).getName() +"\t: Grade:" + grade4);
		}
		else
		{
			System.out.println("Grade card is yet not published.");
		}
		
	}

	@Override
	public StudentCourseChoice selectCourses(int studentId) {
		// TODO Auto-generated method stub
		System.out.println("Following courses are available");
		displayCourseCatalog();
		
		System.out.println("Please select your courses (4 Primary + 2 Alternate):");
		Scanner sc = new Scanner(System.in);
		ArrayList<Course> selectedCourses = new ArrayList<Course>();
		ArrayList<Course> courseCatalog = courseImplementation.getAllCourses();
		
		for(int i=1; i<=6; )
		{
			System.out.println("Enter course(courseId) choice-"+i+":");
			int courseId = sc.nextInt();
			Course course = courseImplementation.getCourseFromCatalog(courseId);
			if(course != null)
			{
				i++;
				selectedCourses.add(course);
			}
			else
			{
				System.out.println("Course not found!!");
			}
			
		}
		
		StudentCourseChoice studentCourseChoice = new StudentCourseChoice();
		studentCourseChoice.setStudentId(studentId);
		studentCourseChoice.setCourses(selectedCourses);
		studentDaoOperation.storeStudentCourseChoice(studentCourseChoice);
		
		System.out.println("Semester Registration Successfull");
		
		return studentCourseChoice;
	}
}
