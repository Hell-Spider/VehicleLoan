package com.lti.dao;

import java.util.List;

import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.LoanAppTable;
import com.lti.model.UserAdvanced;
import com.lti.model.UserBasic;

public interface VehicleDao {
	
	// ADMIN
	// REGISTER
	public void adminRegister(AdminDetails admin);
	
	// ADMIN
	// MODIFY
	public void modifyLoanApplicationStatus(LoanAppTable loanapp);
	public void ApprovedLoan(Approved approved);
	
	// ADMIN
	// VIEW
	public List<UserBasic> showAllUserRegistrationDetails();
	public Account getAccountByEmail(String email);
	
	// USER
	// REGISTER
	public void userRegister(UserBasic userbasic);
	public void ApplyLoan(LoanAppTable loanapp);
	
	// USER
	// MODIFY
	public void passwordReset(UserBasic userbasic);
	
	
	// USER
	// VIEW
	public UserBasic showUserRegistrationInformation(String email);
	public UserAdvanced showUserDetailsInformation(String email);
	public List<LoanAppTable> showAllLoanApplication(String email);
	public LoanAppTable showLoanApplicationByChassis(String chassisNo);

}
