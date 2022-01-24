/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public class AuthorizationService {
	public Role authorize(String username, String password)
	{
		Role role;
		if(username.equals("admin") &&password.equals("password"))
		{
			role = Role.ADMIN;
			return role;
		}
		else
			return null;
	}
}
