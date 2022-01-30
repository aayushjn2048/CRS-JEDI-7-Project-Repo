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
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
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
	private StudentDaoInterface studentDaoImplementation = StudentDaoOperation.getInstance();
	public StudentImplementation(){}

	public static StudentImplementation getInstance(){
		if(instance==null){
			synchronized (StudentImplementation.class){
				instance = new StudentImplementation();
			}
		}
		return instance;
	}

	
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
		StudentDaoInterface studentdao = new StudentDaoOperation();
		ArrayList<Course> clist = studentdao.viewAllCourses();
		System.out.println("Course Id\tCourse Name\tProfessor Id\tCourse Fee");
		 int count = 1;
		 for(Course c : clist)
		 {
			 System.out.println(c.getCourseId()+"\t\t"+c.getName()+"\t\t"+c.getProfessorId()+"\t\t"+c.getCourseFee());
			 count++;
		 }
		
	}

	@Override
	public void displayGradeCard(int studentId) throws GradeCardNotPublishedException {
		// TODO Auto-generated method stub
		
		StudentRegisteredCourses studentRegisteredCourses = courseDaoImplementation.getStudentRegisteredCourses(studentId);
		
		GradeCard gradeCard = gradeCardOperation.getGradeCard(studentId);
		float grade1 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId1());
		float grade2 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId2());
		float grade3 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId3());
		float grade4 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId4());
		
		if(gradeCard.isPublished())
		{
			System.out.println("\n===================GRADE CARD===================\n");
			System.out.println("  Student Id: "+ gradeCard.getStudentId());
			System.out.println("\n  Serial No\tCourse Name\tGrade");
			System.out.println("  1\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId1()).getName() +"\t\t" + grade1);
			System.out.println("  2\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId2()).getName() +"\t\t" + grade2);
			System.out.println("  3\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId3()).getName() +"\t\t" + grade3);
			System.out.println("  4\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId4()).getName() +"\t\t" + grade4);
			System.out.printf("\n  SGPA: %.2f", gradeCard.getSgpa());
			System.out.println("\n=================================================\n");
		}
		else
		{
			throw new GradeCardNotPublishedException();
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
		Map<Integer,Course> courseList = new HashMap<>();
		for(Course c: courseCatalog)
			courseList.put(c.getCourseId(), c);
		for(int i=1; i<=6; )
		{
			System.out.print("Enter course(courseId) choice-"+i+": ");
			int courseId = sc.nextInt();
			
			Course course = null;
			if(courseList.containsKey(courseId))
				course = courseList.get(courseId);
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
		studentDaoImplementation.storeStudentCourseChoice(studentCourseChoice);
		
		System.out.println("Registration form submitted!!");
		
		return studentCourseChoice;
	}

	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		return studentDaoImplementation.studentAlreadyRegistered(studentId);
	}

	@Override
	public void makePaymentSuccessful(int studentId,String referenceNo) {
		studentDaoImplementation.makePaymentSuccessful(studentId,referenceNo);
	}
	
	@Override
	public String getPaymentStatus(int studentId) {

		String paymentStatus = studentDaoImplementation.getPaymentStatus(studentId);
		return paymentStatus;
	}

	@Override
	public Boolean isStudentRegistered(int studentId) {
		return studentDaoImplementation.isStudentRegistered(studentId);
	}
}
