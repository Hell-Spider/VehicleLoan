package com.lti.service;

import java.util.List;

import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.LoanAppTable;
import com.lti.model.UserAdvanced;
import com.lti.model.UserBasic;

public interface VehicleService {
	
	// ADMIN
	// REGISTER
	public void adminRegisterService(AdminDetails admin);
	
	// ADMIN
	// MODIFY
	public void modifyStatus(LoanAppTable loanapp);
	public void AddApprovedDetails(Approved approved);
	
	// ADMIN
	// VIEW
	public List<UserBasic> findAllUserRegistrationDetails(); 
	public Account getAccountByEmailService(String email);
	
	// USER
	// REGISTER
	public void UserRegisterService(UserBasic userbasic);
	public void applyVehicleLoan(LoanAppTable loanapp, UserBasic userbasic, UserAdvanced userdetails);
	
	// USER
	// MODIFY
	public void resetPasswordService(UserBasic userbasic);
	
	// USER
	// VIEW 
	public UserBasic getUserRegistrationdetails(String email);
	public UserAdvanced getUserDetailsService(String email);
	public List<LoanAppTable> getAllLoanApplication(String email);
	public LoanAppTable getLoanApplicationByChassis(String chassisNo);
	
	
	// OTP GENERATE SERVICE
	public String generateOTP();
	
	// EMI CALCULATION
	public double EMICalculate(double loanAmount, int termInYears, double interestRate);
}
