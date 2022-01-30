/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.PaymentImplementation;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.utils.DateAndTimeUtil;

/**
 * @author HP
 *
 */
public class CRSStudentMenu {
	public void studentMenuMain() throws CourseNotFoundException, ProfessorNotFoundException {
		DateAndTimeUtil.loginDisplayDateAndTime();
		System.out.println("\n------------------!!Welcome Student!!-------------------\n");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Course List\n\t2 : Semester Registration\n\t3 : Payment\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int studentId = CRSApplication.getUserId();
		StudentInterface studentImplementation = new StudentImplementation();
		PaymentInterface paymentImplementation = new PaymentImplementation();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 6)
			{
				System.out.println("\nBye!!!");
				DateAndTimeUtil.logoutDisplayDateAndTime();
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
					if(studentImplementation.studentAlreadyRegistered(CRSApplication.getUserId()))
					{
						System.out.println("You already submitted the registration form");
						break;
					}
					System.out.print("Enter email: ");
					String email = scanner.next();
					System.out.print("Enter contact number: ");
					String contaccNo = scanner.next();

					studentImplementation.selectCourses(CRSApplication.getUserId());
					break;
				}
				case 3:{
					//Payment
					String paymentStatus=studentImplementation.getPaymentStatus(studentId);
					if(!paymentStatus.equals("SUCCESS")) {
						System.out.println("Fees to be paid for current semester is: " + paymentImplementation.calculateFees(studentId));
						String referenceNo = paymentImplementation.displayPaymentMethods();
						studentImplementation.makePaymentSuccessful(CRSApplication.getUserId(),referenceNo);
					}
					System.out.println("Payment done successfully!");
					break;
				}
				case 4:{
					//System.out.println("Grade card for current semester is: ");
					try {
						if(studentImplementation.isStudentRegistered(CRSApplication.getUserId()))
							studentImplementation.displayGradeCard(studentId);
						else
							System.out.println("Student not registered");
					}
					catch(GradeCardNotPublishedException g)
					{}
					break;
				}
				case 5: {
					//view profile
					System.out.println("\n===============STUDENT PROFILE===============\n");
					Student student = studentImplementation.viewStudentDetails(studentId);
					System.out.println("\tStudentId:\t" + student.getStudentId());
					System.out.println("\tName:\t\t" + student.getName());
					System.out.println("\tContact:\t" + student.getContactNo());
					System.out.println("\tAddress:\t" + student.getAddress());
					System.out.println("\n=============================================\n");
				}
			}
		}
	}
}
