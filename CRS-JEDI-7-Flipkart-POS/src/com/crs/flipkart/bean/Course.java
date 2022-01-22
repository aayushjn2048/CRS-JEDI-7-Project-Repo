/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class Course {
	private int courseId;
	private String name;
	private int professorId;
	private int offeredSemester;
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the professorId
	 */
	public int getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the offeredSemester
	 */
	public int getOfferedSemester() {
		return offeredSemester;
	}
	/**
	 * @param offeredSemester the offeredSemester to set
	 */
	public void setOfferedSemester(int offeredSemester) {
		this.offeredSemester = offeredSemester;
	}
}
