package com.lti.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.VehicleDao;
import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.LoanAppTable;
import com.lti.model.UserAdvanced;
import com.lti.model.UserBasic;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleDao dao = null;
	
	
	// ADMIN
	// REGISTER
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void adminRegisterService(AdminDetails admin) {
		dao.adminRegister(admin);
	}
	
	// ADMIN
	// MODIFY
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyStatus(LoanAppTable loanapp) {
		dao.modifyLoanApplicationStatus(loanapp);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void AddApprovedDetails(Approved approved) {
		dao.ApprovedLoan(approved);
	}
	
	
	// ADMIN
	// VIEW
	@Override
	public List<UserBasic> findAllUserRegistrationDetails() {
		return dao.showAllUserRegistrationDetails();
	}
	@Override
	public Account getAccountByEmailService(String email) {
		return dao.getAccountByEmail(email);
	}
	
	// USER
	// REGISTER
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void UserRegisterService(UserBasic userbasic) {
		dao.userRegister(userbasic);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void applyVehicleLoan(LoanAppTable loanapp, UserBasic userbasic, UserAdvanced userdetails) {
		userbasic.setUserdetails(userdetails);
		loanapp.setUser(userdetails);
		dao.ApplyLoan(loanapp);	
	}

	
	// USER
	// MODIFY
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void resetPasswordService(UserBasic userbasic) {
		dao.passwordReset(userbasic);
	}
	
	// USER
	// VIEW
	@Override
	public UserBasic getUserRegistrationdetails(String email) {
		return dao.showUserRegistrationInformation(email);
	}
	@Override
	public UserAdvanced getUserDetailsService(String email) {
		return dao.showUserDetailsInformation(email);
	}
	@Override
	public List<LoanAppTable> getAllLoanApplication(String email) {
		return dao.showAllLoanApplication(email);
	}
	@Override
	public LoanAppTable getLoanApplicationByChassis(String chassisNo) {
		return dao.showLoanApplicationByChassis(chassisNo);
	}

	
	// OTP SERVICE
	// FORGOT PASSWORD
	@Override
	public String generateOTP()
	{
		int randomPin = (int) (Math.random()*900000)+100000; 
        String otp  = String.valueOf(randomPin); 
        return otp;
	}

	
	// EMI CALCULATION
	@Override
	public double EMICalculate(double loanAmount, int termInYears, double interestRate) {
		interestRate /= 100.0;
		double monthlyRate = interestRate / 12.0;
		int termInMonths = termInYears * 12;
		double monthlyPayment = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
		return BigDecimal.valueOf(monthlyPayment).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}








}
