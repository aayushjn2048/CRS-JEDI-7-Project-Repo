/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;

/**
 * @author HP
 *
 */
public class CRSStudentMenu {
	public void studentMenuMain() {
		System.out.println("\n------------------!!Welcome Student!!-------------------\n");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Course List\n\t2 : Semester Registration\n\t3 : Choose courses\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int studentId = CRSApplication.getUserId();
		StudentImplementation studentImplementation = new StudentImplementation();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 6)
			{
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
				case 1:{
					studentImplementation.displayCourseCatalog();
					break;
				}
				case 2: {
					//Semester Registration
					break;
				}
				case 3:{
					//Select Courses
					break;
				}
				case 4:{
					//View Grade Card
					break;
				}
				case 5: {
					//view profile
				}
			}
		}
	}
}
