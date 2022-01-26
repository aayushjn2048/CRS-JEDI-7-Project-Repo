/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public interface AdminDaoInterface {
	
	public Boolean addCourse(Course course);
	public Boolean deleteCourse(int courseId);
	public Boolean updateCourse(Course course);
	public Boolean addProfessor(Professor professor);
	public Boolean removeProfessor(int professorId);
	public Boolean updateProfessor(Professor professorOld,Professor professorNew);
	public Boolean addStudentCourseChoices(int studentId,ArrayList<Integer> courseList);
	public Boolean updateAllocatedStudentCourses(Map<Integer,ArrayList<Integer>> list);
	public ArrayList<Course> getAllCourseDetails();
	public Map<Integer,ArrayList<Course>> getAllCourseChoices();
	public void viewAllProfessor();
	public ArrayList<Course> viewAllCourses();
	public boolean studentSelfRegistration(int studentId);
}
