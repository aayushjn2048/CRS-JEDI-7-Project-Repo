/**
 * 
 */
package com.crs.flipkart.application;

import java.util.*;

import com.crs.flipkart.business.AuthorizationService;
import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */
	private static int userId = 0;

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int val) {
		userId = val;
	}

	public static void startApplication() {
		System.out.println("\n----------------!!Welcome to CRS Application!!----------------\n");
		System.out.println("Choose the following operation:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Login User\n\t2 : Self Register Student\n\t3 : Forgot/Change Password\n\t4 : Quit");
		System.out.println("==============================================================");
		Scanner scanner = new Scanner(System.in);
		AuthorizationService authorizationService = new AuthorizationService();
		while (true) {
			System.out.print("Enter Choice Number: ");
			int choice = scanner.nextInt();
			if (choice == 4)
				break;
			switch (choice) {
			case 1: {
				System.out.println("Enter your Login details");
				System.out.print("Enter Username: ");
				String username = scanner.next();
				System.out.print("Enter Password: ");
				String password = scanner.next();
				Role role = authorizationService.authorize(username, password);
				if (role != null && role.equals(Role.ADMIN)) {
					CRSAdminMenu adminMenu = new CRSAdminMenu();
					adminMenu.adminMenuMain();
				} else if (role != null && role.equals(Role.PROFESSOR)) {
					CRSProfessorMenu professorMenu = new CRSProfessorMenu();
					professorMenu.professorMenuMain();
				} else if (role != null && role.equals(Role.STUDENT)) {
					CRSStudentMenu studentMenu = new CRSStudentMenu();
					studentMenu.studentMenuMain();
					return;
				} else
					System.out.println("\nWrong credentials!!!\n");
				break;
			}
			case 2: {
				// Register Student Portal
				StudentSelfRegistration studentSelfRegistration = new StudentSelfRegistration();
				studentSelfRegistration.selfRegistration();
				break;
			}
			case 3: {
				// Forgot password implementation
				break;
			}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startApplication();
	}

}
