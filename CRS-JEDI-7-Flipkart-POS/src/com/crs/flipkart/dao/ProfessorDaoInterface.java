/**
 * 
 */
package com.crs.flipkart.dao;

/**
 * @author HP
 *
 */
public interface ProfessorDaoInterface {
public void viewEnrolledStudents(int professorId);
	
	public boolean selectCourse(int professorId, int courseId);
	
	public void assignGrade(int studentId, int courseId, int semesterNumber);
}
