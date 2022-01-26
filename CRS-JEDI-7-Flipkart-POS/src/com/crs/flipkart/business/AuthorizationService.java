/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AuthorizeDaoInterface;
import com.crs.flipkart.dao.AuthorizeDaoOperation;

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
		AuthorizeDaoInterface auth = new AuthorizeDaoOperation();
		return auth.authorizeUser(username, password);
		
	}
}
