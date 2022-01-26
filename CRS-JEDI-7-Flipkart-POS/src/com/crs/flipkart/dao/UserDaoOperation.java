/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crs.flipkart.bean.Student;

/**
 * @author HP
 *
 */
public class UserDaoOperation implements UserDaoInterface {
	
	Connection conn = DBConnection.connectDB();
	
	@Override
	public boolean addUsertData(Student user) {
		
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO user(userId,username,password,name,address,gender,contactNO,role) values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPasswordHash());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getAddress());
			stmt.setString(6, user.getGender().toString());
			stmt.setString(7, user.getContactNo());
			stmt.setString(8, user.getRole().toString());
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

}
