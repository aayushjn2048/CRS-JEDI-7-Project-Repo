/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.dao.StudentDaoInterface;

/**
 * @author HP
 *
 */
public class StudentImplementation implements StudentInterface{
	
	private static StudentImplementation instance = null;

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
	
	private static ArrayList<Student> studentData = new ArrayList<Student>();
	private static Map<Integer,ArrayList<Course>> courseChoices = new HashMap<>();
	private static ArrayList<StudentRegisteredCourses> registeredCoursesData = new ArrayList<StudentRegisteredCourses>();
	private static Map<Integer,ArrayList<Integer>> registeredCourseChoices = new HashMap<>();
	
	
	public void addStudentdata(Student student) {
		StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
		if(studentDaoOperation.addStudentData(student)) {
			System.out.println("student is added");
		}
	}
	
	public static Map<Integer,ArrayList<Integer>> viewRegisteredCourseChoices()
	{
		return registeredCourseChoices;
	}
	
	public static void updateRegisteredCourseChoices(Map<Integer,ArrayList<Integer>> data)
	{
		registeredCourseChoices = data;
	}
	
	public Student viewStudentDetails(int studentId)
	{
		return studentDaoImplementation.viewStudentDetails(studentId);
	}
	public ArrayList<Student> viewStudentData()
	{
		return studentDaoImplementation.viewAllStudents();
	}
	public static void updateStudentData(ArrayList<Student> studentList)
	{
		studentData = studentList;
	}
	public static ArrayList<Course> viewCourseChoicesForStudent(int studentId)
	{
		return courseChoices.get(studentId);
	}
	public static Map<Integer,ArrayList<Course>> viewAllCourseChoices()
	{
		return courseChoices;
	}
	public static void updateCourseChoices(Map<Integer,ArrayList<Course>> choiceList)
	{
		courseChoices = choiceList;
	}
	public static ArrayList<StudentRegisteredCourses> viewRegisteredCourses()
	{
		return registeredCoursesData;
	}
	public static void updateRegisteredCourses(ArrayList<StudentRegisteredCourses> newList)
	{
		registeredCoursesData = newList;
	}
	public void activateGradeCard(){
		try {
			studentDaoImplementation.activateGradeCard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deactivateGradeCard(){
		for(int i =0; i<studentData.size(); i++){
			studentData.get(i).setGradeCardVisibility(false);
		}
	}
}
