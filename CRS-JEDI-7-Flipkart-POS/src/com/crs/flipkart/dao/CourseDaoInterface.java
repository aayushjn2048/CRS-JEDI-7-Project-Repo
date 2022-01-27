/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;

/**
 * @author HP
 *
 */
public interface CourseDaoInterface {
	public void addCourse(Course course);

    public void deleteCourse(int courseId);

    public void updateCourse(Course course);
    
    public StudentRegisteredCourses getStudentRegisteredCourses(int studentId);
    
    public Course getCourseFromCourseId(int courseId);
    
    public ArrayList<Course> getAllCourse();
}
