/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;

import com.crs.flipkart.bean.GradeCard;

/**
 * @author HP
 *
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface{
	
	private static GradeCardDaoOperation instance = null;
    private Connection conn = DBConnection.connectDB();

    private GradeCardDaoOperation() {
    }

    public static GradeCardDaoOperation getInstance() {
        if (instance == null) {
            synchronized (GradeCardDaoOperation.class) {
                instance = new GradeCardDaoOperation();
            }
        }
        return instance;
    }
	

	@Override
	public GradeCard getGradeCard(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGradeFromCourseId(int studentId, int CourseId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
