package com.lti.service;

import java.util.Date;
import java.util.List;

import com.lti.dto.LoginDto;
import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.EmiClass;
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
	public List<LoanAppTable> viewAllAcceptedLoanApplications();
	public List<LoanAppTable> viewAllRejectedLoanApplications();
	
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
	public List<Approved> viewAllApprovedByEmail(String email);
	public LoanAppTable getLoanApplicationByChassis(String chassisNo);
	public Approved viewApprovedByLoanId(int loanId);
	
	
	// USER LOGIN
	public boolean verifyLogin(LoginDto login);
	
	
	// OTP GENERATE SERVICE
	public String generateOTP();
	
	// EMI CALCULATION
	public double EMICalculate(double loanAmount, int termInYears, double interestRate);
	
	// EMI LIST
	public List<EmiClass> calculateEmi(double loanAmount, int termInYears, double interestRate,Date appdate);
}
