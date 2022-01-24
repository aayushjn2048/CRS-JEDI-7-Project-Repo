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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to CRS Application");
		System.out.println("Choose the following operation:-\n1 : Login User\n2 : Self Register Student\n3 : Forgot/Change Password\n4 : Quit");
		Scanner scanner = new Scanner(System.in);
		AuthorizationService authorizationService = new AuthorizationService();
		while(true)
		{
			System.out.print("Enter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice==4)
				break;
			switch(choice)
			{
				case 1: {
							System.out.println("Enter your Login details");
					  		System.out.print("Enter Username: ");
					  		String username = scanner.next();
					  		System.out.print("Enter Password: ");
					  		String password = scanner.next();
					  		Role role = authorizationService.authorize(username, password);
					  		if(role!=null&&role.equals(Role.ADMIN))
					  		{
					  			CRSAdminMenu adminMenu = new CRSAdminMenu();
					  			adminMenu.adminMenuMain();
					  			System.out.println("Back to CRS Application");
					  		}
					  		else
					  			System.out.println("Retry Logging in");
					  		break;
					}
				case 2: {
							//Register Student Portal
							break;
				}
				case 3: {
							//Forgot password implementation
							break;
				}
			}
		}
	}

}
