/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.ProfessorImplementation;

/**
 * @author HP
 *
 */
public class CRSProfessorMenu {
	
	public void professorMenuMain() {
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
					System.out.println("Enter StudentId");
					int studentId= scanner.nextInt();
					System.out.println("Enter CourseId");
					int courseId= scanner.nextInt();
					System.out.println("Enter SemesterNumber");
					int semesternumber= scanner.nextInt();
					professorImplementation.assignGrade(studentId,courseId,semesternumber);
					break;
				}
			}
		}
	}
}
