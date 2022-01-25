/**
 *
 */
package com.crs.flipkart.bean;

/**
 * The type Semester registration.
 */
public class SemesterRegistration {
    private int studentId;
    private int semesterNo;
    private String dateOfRegistration;
    private int totalFee;

	/**
	 * Gets total fee.
	 *
	 * @return the total fee
	 */
	public int getTotalFee() {
        return totalFee;
    }

	/**
	 * Sets total fee.
	 *
	 * @param totalFee the total fee
	 */
	public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

	/**
	 * Gets student id.
	 *
	 * @return the student id
	 */
	public int getStudentId() {
        return studentId;
    }

	/**
	 * Sets student id.
	 *
	 * @param studentId the student id
	 */
	public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

	/**
	 * Gets semester no.
	 *
	 * @return the semester no
	 */
	public int getSemesterNo() {
        return semesterNo;
    }

	/**
	 * Sets semester no.
	 *
	 * @param semesterNo the semester no
	 */
	public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

	/**
	 * Gets date of registration.
	 *
	 * @return the date of registration
	 */
	public String getDateOfRegistration() {
        return dateOfRegistration;
    }

	/**
	 * Sets date of registration.
	 *
	 * @param dateOfRegistration the date of registration
	 */
	public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
