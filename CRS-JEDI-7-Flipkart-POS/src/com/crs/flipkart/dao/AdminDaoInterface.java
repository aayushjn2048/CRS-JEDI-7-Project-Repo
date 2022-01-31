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
	
	public Boolean addCourse(Course course) throws DatabaseException;
	public Boolean deleteCourse(int courseId) throws DatabaseException;
	public Boolean updateCourse(Course course) throws DatabaseException;
	public Boolean addProfessor(Professor professor) throws DatabaseException;
	public Boolean removeProfessor(int professorId) throws DatabaseException;
	public Boolean updateProfessor(Professor professorOld,Professor professorNew) throws DatabaseException;
	public Boolean addStudentCourseChoices(int studentId,ArrayList<Integer> courseList);
	public Boolean updateAllocatedStudentCourses(Map<Integer,ArrayList<Integer>> list);
	public ArrayList<Course> getAllCourseDetails();
	public Map<Integer,ArrayList<Course>> getAllCourseChoices();
	public ArrayList<Professor> getAllProfessorDetails();
	public ArrayList<Course> viewAllCourses();
	public boolean studentSelfRegistration(int studentId);
	public Boolean studentAlreadyRegistered(int studentId);
	public Boolean updatePaymentStatus(ArrayList<Integer> studentList);
}
