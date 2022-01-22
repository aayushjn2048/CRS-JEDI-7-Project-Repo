/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Professor;

/**
 * @author HP
 *
 */
public class ProfessorImplementation {
	private static ArrayList<Professor> professorData = new ArrayList<Professor>();
	public static ArrayList<Professor> viewProfessorData()
	{
		return professorData;
	}
}
