/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;

/**
 * @author HP
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	Connection conn = DBConnection.connectDB();
	PreparedStatement stmt = null;

	@Override
	public Boolean addCourse(Course course) {

		try {
			String sql = "INSERT INTO courseCatalog(name,offeredSemester) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getOfferedSemester());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	
//	------------------------Added @Vivek---------------------------
	@Override
	public Boolean deleteCourse(int courseId) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM courseCatalog WHERE courseId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);		//we can only pass string as argument in setString
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	@Override
	//Considers the fact that courseId cannot be changed
	public Boolean updateCourse(Course courseOld,Course courseNew) {
		// TODO Auto-generated method stub
		String sql = "UPDATE courseCatalog SET name = ? , offeredSemester = ? , professorId = ? WHERE courseId = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseNew.getName());
			stmt.setInt(2, courseNew.getOfferedSemester());
			stmt.setInt(3, courseNew.getProfessorId());
			stmt.setInt(4, courseOld.getCourseId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	@Override
	public Boolean addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "INSERT INTO user(username,password,name,address,gender,contactNo,role) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professor.getUsername());
			stmt.setString(2, professor.getPasswordHash());
			stmt.setString(3, professor.getName());
			stmt.setString(4, professor.getAddress());
			stmt.setString(5, professor.getGender().toString());
			stmt.setString(6, professor.getContactNo());
			stmt.setString(7, professor.getRole().toString());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		
		
		
		//get the id of this professor from the DB
		
		
		//add this professor and his designation to the professor table
		
		
		
		return false;
	}

	@Override
	public Boolean removeProfessor(int professorId) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM professor WHERE courseId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);		
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	@Override
	public Boolean updateProfessor(Professor professorOld,Professor professorNew) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "UPDATE user SET username = ? ,password = ? ,name = ? ,address = ? ,contactNo = ? WHERE userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professorNew.getUsername());
			stmt.setString(2, professorNew.getPasswordHash());
			stmt.setString(3, professorNew.getName());
			stmt.setString(4, professorNew.getAddress());
			stmt.setString(5, professorNew.getContactNo());
			stmt.setInt(6, professorOld.getProfessorId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		
		if(!professorOld.getDesignation().equals(professorNew.getDesignation())) {
			
			try {
				String sql = "UPDATE professor SET designation = ? WHERE professorId = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, professorNew.getDesignation().toString());
				stmt.setInt(2, professorOld.getProfessorId());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
				return true;
				
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
				return false;
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
				return false;
			} finally {
				// finally block used to close resources // nothing we can do//end finally try
			}
			
		}
		
		return true;
		
	}

}
