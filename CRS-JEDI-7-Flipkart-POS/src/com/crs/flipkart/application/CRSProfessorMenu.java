/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;


import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.utils.DateAndTimeUtil;
/**
 * 
 * @author JEDI-03
 * Class that display Professor Menu
 * 
 */
public class CRSProfessorMenu {
	
	
	/**
	 * Method to create Professor menu
	 * @throws CourseNotFoundException, ProfessorNotFoundException
	 */
	
	public void professorMenuMain() throws CourseNotFoundException, ProfessorNotFoundException{
		DateAndTimeUtil.loginDisplayDateAndTime();
		System.out.println("\n------------------!!Welcome Professor!!-------------------\n");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Available Courses\n\t2 : View enrolled students\n\t3 : Select Course\n\t4 : Assign Grade\n\t5 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int professorId = CRSApplication.getUserId();
		ProfessorImplementation professorImplementation = new ProfessorImplementation();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 5)
			{
				System.out.println("\nBye!!!");
				DateAndTimeUtil.logoutDisplayDateAndTime();
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
				case 1:{
					professorImplementation.viewAvailableCourses();
					break;
				}
				case 2: {
					professorImplementation.viewEnrolledStudents(professorId);
					break;
				}
				case 3:{
					while(true) {
						System.out.print("Please enter your choice of selection: ");
						int courseId=scanner.nextInt();
						if(professorImplementation.selectCourse(professorId,courseId)) {
							System.out.println("Succesfully allocated");
							break;
						}
						else {
							System.out.println("Course is already taken");
						}
					}
					break;
				}
				case 4:{
					System.out.print("Enter StudentId: ");
					int studentId= scanner.nextInt();
					System.out.print("Enter CourseId: ");
					int courseId= scanner.nextInt();
					System.out.print("Enter Grade: ");
					float grade= scanner.nextFloat();
					professorImplementation.assignGrade(studentId,courseId,grade);
					break;
				}
			}
		}
	}
}
