/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author HP
 *
 */
public class SqlQueryConstants {
    public static final String ADD_COURSE_QUERY = "INSERT INTO course(courseId,name,courseFee) values(?,?,?)";
    public static final String ADD_COURSE_TO_CATALOG_QUERY = "INSERT INTO catalog(catalogId,courseId) values(?,?)";
    public static final String DELETE_COURSE_QUERY = "DELETE FROM course WHERE courseId = ?";
    public static final String VIEW_PROFESSORS_QUERY = "SELECT * FROM professor";
    public static final String VIEW_COURSES_QUERY = "SELECT * FROM course";
    public static final String ADD_PROFESSOR_QUERY = "INSERT INTO professor(professorId,name,address,gender,contactNo,designation) values(?,?,?,?,?,?)";
    public static final String ADD_USER_QUERY = "INSERT INTO user(userId,username,password) VALUES(?,?,?)";
    public static final String ADD_ROLE_QUERY = "INSERT INTO role(userId,roleName) VALUES(?,?)";
    public static final String DELETE_USER_QUERY = "DELETE FROM user WHERE userId = ?";
    public static final String DELETE_PROFESSOR_QUERY = "DELETE FROM professor WHERE professorId = ?";
}