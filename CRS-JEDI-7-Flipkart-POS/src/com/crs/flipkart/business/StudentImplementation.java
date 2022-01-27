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
			System.out.println("Course-1: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId1()) +"\t: Grade:" + grade1);
			System.out.println("Course-2: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId2()) +"\t: Grade:" + grade1);
			System.out.println("Course-3: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId3()) +"\t: Grade:" + grade1);
			System.out.println("Course-4: "+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId4()) +"\t: Grade:" + grade1);
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
	public void makePaymentSuccessful(int studentId) {
		studentDaoImplementation.makePaymentSuccessful(studentId);
	}
}
