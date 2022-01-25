/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author HP
 *
 */
public class DBConnection {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://crs-database.ca10tiiqurow.ap-south-1.rds.amazonaws.com/CRS";

	   //  Database credentials
	   static final String USER = "ce";
	   static final String PASS = "Crs12345#";
	   
	   public static Connection connectDB() {
			
			// Step 2 
			// Declare the Coneection or prepaidstatement variable here 
			   Connection conn = null;
			   PreparedStatement stmt = null;
			   
			   try{
				   
				   // Step 3 Regiater Driver here and create connection 
				      Class.forName("com.mysql.jdbc.Driver");

				   // Step 4 make/open  a connection 
				   
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);				      
				      return conn;
				      
				   }catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e){
				      //Handle errors for Class.forName
				      e.printStackTrace();
				   }finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            stmt.close();
				      }catch(SQLException se2){
				      }// nothing we can d
				   }//end try
				   System.out.println("Goodbye!");
				   return null;
				}//end main
}
