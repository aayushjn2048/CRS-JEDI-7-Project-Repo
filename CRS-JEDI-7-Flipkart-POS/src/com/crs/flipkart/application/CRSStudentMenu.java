/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.PaymentImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.StudentInterface;

/**
 * @author HP
 *
 */
public class CRSStudentMenu {
	private StudentInterface studntImplementation = StudentImplementation.getInstance();

	public void studentMenuMain() {
		System.out.println("\n------------------!!Welcome Student!!-------------------\n");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println(
				"\t1 : View Course List\n\t2 : Semester Registration\n\t3 : Payment\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int studentId = CRSApplication.getUserId();
		StudentImplementation studentImplementation = new StudentImplementation();
		PaymentImplementation paymentImplementation = new PaymentImplementation();
		while (true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if (choice == 6) {
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch (choice) {
			case 1: {
				studentImplementation.displayCourseCatalog();
				break;
			}
			case 2: {
				// Semester Registration
				System.out.println("Please Enter email");
				String email = scanner.next();
				System.out.println("Please Enter contact email");
				String contaccNo = scanner.next();

				studentImplementation.selectCourses(CRSApplication.getUserId());

				break;
			}
			case 3: {
				// Payement
				System.out.println(
						"Fees to be paid for current semester is: " + paymentImplementation.calculateFees(studentId));
				break;
			}
			case 4: {
				// View Grade Card
				System.out.println("Grade card for current semester is: ");
				studentImplementation.displayGradeCard(studentId);
				break;
			}
			case 5: {
				// view profile
				System.out.println("Profile: ");
				Student student = studentImplementation.viewStudentDetails(studentId);
				System.out.println("\tStudentId: " + student.getUserId());
				System.out.println("\tName: " + student.getName());
				System.out.println("\tContact: " + student.getContactNo());
				System.out.println("\tAddress: " + student.getAddress());
			}
			}
		}
	}
}
