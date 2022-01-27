/**
 *
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author HP
 *
 */
public class UpdaterDaoImplementation {

    Connection conn = DBConnection.connectDB();

    public Boolean update(String username,String oldPassword,String newPassword) {
        PreparedStatement stmt = null;

        try {

            {
                String sql = "SELECT * FROM user WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                boolean ok = false;
                while (rs.next()) {
                    ok = true;
                    System.out.println("Username exists");
                }
                if(!ok){
                    System.out.println("Username Doesn't exist !");
                    return false;
                }
            }

            {
                String sql = "SELECT password FROM user WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String s = rs.getString("password");
                    if (!s.equals(oldPassword)) {
                        System.out.println("Wrong original Password");
                        return false;
                    }
                }
            }

            {
                String sql = "UPDATE user SET password = ? WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, newPassword);
                stmt.setString(2, username);
                int sz = stmt.executeUpdate();
            }
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

        return true;
    }
}