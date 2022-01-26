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
		System.out.println("\n----------------!!Welcome Professor!!----------------\n");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : viewAllCourses\n\t2 : viewEnrolledStudents\n\t3 : selectCourse\n\t4 : assignGrade");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter professorId");
		int professorId = scanner.nextInt();
		ProfessorImplementation professorImplementation = new ProfessorImplementation();
		while(true) {
			System.out.println("Welocme choice");
			int choice = scanner.nextInt();
			if(choice==5) {
				break;
			}
			switch(choice) {
				case 1:{
					professorImplementation.viewAllCourses();
					break;
				}
				case 2: {
					professorImplementation.viewEnrolledStudents(professorId);
					break;
				}
				case 3:{
					while(true) {
						System.out.println("Please enter your choice of selection");
						int courseId=scanner.nextInt();
						if(professorImplementation.selectCourse(professorId,courseId)) {
							System.out.println("Succesfully allocated");
							break;
						}
						else {
							System.out.println("Course is already taken Please choose some other");
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
