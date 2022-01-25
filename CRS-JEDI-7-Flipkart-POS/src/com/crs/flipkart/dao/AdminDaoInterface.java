/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public interface AdminDaoInterface {
	
	public Boolean addCourse(Course course);
	public Boolean deleteCourse(int courseId);
	public Boolean updateCourse(Course courseOld,Course courseNew);
	public Boolean addProfessor(Professor professor);
	public Boolean removeProfessor(int professorId);
	public Boolean updateProfessor(Professor professorOld,Professor professorNew);
}
