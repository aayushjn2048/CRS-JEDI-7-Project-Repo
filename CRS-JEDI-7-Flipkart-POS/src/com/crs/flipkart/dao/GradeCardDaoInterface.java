/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.GradeCard;

/**
 * @author HP
 *
 */
public interface GradeCardDaoInterface {
	public GradeCard getGradeCard(int studentId);
	public float getGradeFromCourseId(int studentId, int CourseId);
}
