/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;

/**
 * @author HP
 *
 */
public interface ProfessorDaoInterface {
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorId);
	
	public boolean selectCourse(int professorId, int courseId);
	public ArrayList<Course> viewAvailableCourses();
	public void assignGrade(int studentId, int courseId, int semesterNumber);
}
