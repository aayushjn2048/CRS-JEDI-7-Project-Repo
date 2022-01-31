/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.exceptions.CourseNotFoundException;

/**
 * 
 * @author JEDI-03
 * Interface for Course Operations
 * 
 */
public interface CourseInterface {
	
	 /**
	 * Method for Removing Course to Catalog DataBase
	 * @param courseId
	 * throws CourseNotFoundException
     */
	public void removeCourse(int courseId) throws CourseNotFoundException;
	
	/**
	 * Method for Adding Course to Catalog DataBase
	 * @param course the course object whose details need to be added
	 */
    public void addCourse(Course course);
    
    /**
	 * Method for Updating Course to Catalog DataBase
	 * @param course object
	 * @throws CourseNotFoundException
	 */
    public void updateCourse(Course course) throws CourseNotFoundException;
    
    /**
	 * Method to get all the courses
	 * @return List of courses
	 */
    public ArrayList<Course> getAllCourses();
    
    /**
	 * Method to get the course from course catalog
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return course object
	 */
    public Course getCourseFromCatalog(int courseId) throws CourseNotFoundException;
}
