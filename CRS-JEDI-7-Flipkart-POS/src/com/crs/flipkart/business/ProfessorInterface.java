/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author HP
 * Interface for ProfessorImplementation
 * 
 */
public interface ProfessorInterface {
	
	/**
	 * Method to view all courses
	 */
	public void viewAllCourses();
	
	/**
	 * Method to view list of enrolled Students
	 */
	public void viewEnrolledStudents();
	
	/**
	 * Method to get Courses by Professor Id
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return boolean data whether offered to the professor or not.
	 */
	public boolean selectCourse(int professorId, int courseId) ;
	
	/**
	 * Assign grades to student
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 */
	public void assignGrade(int studentId, int courseId, int semesterNumber);
}
