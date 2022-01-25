/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public interface CourseInterface {

    public void removeCourse(int courseId);
    public void addCourse(Course course);
    public void updateCourse(Course course);

}
