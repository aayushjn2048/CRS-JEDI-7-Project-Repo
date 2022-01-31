/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author HP
 * Interface for PaymentImplementation 
 * 
 */
public interface PaymentInterface {
	
	/**
	 * Method to calculate the total fee for student
	 * @param StudentId
	 * @return totalFee
	 */
	public int calculateFees(int studentId);
}
