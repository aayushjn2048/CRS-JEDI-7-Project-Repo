/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminImplementation;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.UserImplementation;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public class StudentSelfRegistration {

	/**
	 * @param args
	 */
	public void selfRegistration()  {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Welcome ");
		System.out.print("Enter userid: ");
		int userid = scanner.nextInt();
		
		System.out.print("Enter username: ");
		String username = scanner.next();
		
		System.out.print("Enter password: ");
		String passwordHash = scanner.next();
		
		System.out.print("Enter name: ");
		String name = scanner.next();
		
		System.out.print("Enter address: ");
		String address = scanner.next();
		
		System.out.print("Enter contact number: ");
		String contactNo = scanner.next();
		
		System.out.print("Enter Gender: ");
		String gen=scanner.next();
		Gender gender = Gender.stringToGender(gen);
		
		System.out.print("Enter Role: ");
		String rol=scanner.next();
		Role role = Role.stringToName(rol);
		
		
		
		int StudentId=userid;
		AdminInterface adminimplementation = new AdminImplementation();
		if(adminimplementation.approveStudentRegistration(StudentId)) {
			System.out.println("Student is registerd succesfully");
			StudentImplementation studentImplementation = new StudentImplementation();
			
			Student user= new Student();
			user.setUserId(userid);
			user.setUsername(username);
			user.setPasswordHash(passwordHash);
			user.setName(name);
			user.setAddress(address);
			user.setContactNo(contactNo);
			user.setGender(gender);
			user.setRole(role);
			UserImplementation userImplementation = new UserImplementation();
			userImplementation.addUserdata(user);
			Student student = new Student();
			student.setGradeCardVisibility(false);
			student.setStudentId(StudentId);
			studentImplementation.addStudentdata(student);
		}
		else {
			System.out.println("Please retry");
		}
		
	}
}
