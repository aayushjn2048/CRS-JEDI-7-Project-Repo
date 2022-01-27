/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.crs.flipkart.application.CRSApplication;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author HP
 *
 */
public class AuthorizeDaoOperation implements AuthorizeDaoInterface{

	private Connection conn = DBUtils.getConnection();
	@Override
	public Role authorizeUser(String username, String password) {
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM user join role ON (user.userid = role.userid) WHERE username = ? AND password = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 	CRSApplication.setUserId(rs.getInt("userId"));
		            return Role.stringToName(rs.getString("roleName"));
			 }
			}
			catch(Exception e){
				
			}
		return null;
	}

}
