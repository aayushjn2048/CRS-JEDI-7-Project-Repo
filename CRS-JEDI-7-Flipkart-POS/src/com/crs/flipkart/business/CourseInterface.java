/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;

/**
 * @author HP
 *
 */
public interface CourseInterface {
	public boolean removeCourse(int courseId);
    public void addCourse(Course course);
    public void updateCourse(Course course);
}
