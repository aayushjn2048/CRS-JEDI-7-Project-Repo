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

/**
 * @author HP
 *
 */
public class StudentImplementation {
	private static ArrayList<Student> studentData = new ArrayList<Student>();
	private static Map<Integer,ArrayList<Course>> courseChoices = new HashMap<>();
	private static ArrayList<StudentRegisteredCourses> registeredCoursesData = new ArrayList<StudentRegisteredCourses>();
	public static Student viewStudentDetails(int studentId)
	{
		for(Student student: studentData)
		{
			if(student.getStudentId()==studentId)
				return student;
		}
		return null;
	}
	public static ArrayList<Student> viewStudentData()
	{
		return studentData;
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
	public static void activateGradeCard(){
		for(int i =0; i<studentData.size(); i++){
			studentData.get(i).setGradeCardVisibility(true);
		}
	}
	public static void deactivateGradeCard(){
		for(int i =0; i<studentData.size(); i++){
			studentData.get(i).setGradeCardVisibility(false);
		}
	}
}
