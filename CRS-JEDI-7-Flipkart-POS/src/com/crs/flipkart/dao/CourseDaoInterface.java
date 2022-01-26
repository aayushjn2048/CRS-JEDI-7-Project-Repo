/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Course;

/**
 * @author HP
 *
 */
public interface CourseDaoInterface {
	public void addCourse(Course course);

    public void deleteCourse(int courseId);

    public void updateCourse(Course course);
}
