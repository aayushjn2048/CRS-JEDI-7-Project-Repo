/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author HP
 *
 */
public interface ProfessorInterface {
	
	public void viewAllCourses();
	
	public void viewEnrolledStudents();
	
	public boolean selectCourse(int professorId, int courseId) ;
	
	public void assignGrade(int studentId, int courseId, int semesterNumber);
}
