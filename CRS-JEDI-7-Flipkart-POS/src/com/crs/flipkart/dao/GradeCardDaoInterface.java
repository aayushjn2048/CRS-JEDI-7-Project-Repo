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
	public int getGradeFromCourseId(int studentId, int CourseId);
}