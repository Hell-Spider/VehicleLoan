package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.LoanAppTable;
import com.lti.model.UserAdvanced;
import com.lti.model.UserBasic;
import com.lti.service.MailServiceImpl;
import com.lti.service.VehicleService;

@RestController
@RequestMapping(path = "users")
@CrossOrigin
public class VehicleController {
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private VehicleService service;
	private MailServiceImpl mail = new MailServiceImpl();
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ADMIN
	// REGISTER
	// http://localhost:9091/VehicleLoanApp/users/RegisterAdmin
	@GetMapping("/RegisterAdmin")
	public void addAdmin(@RequestBody AdminDetails admin)
	{
		service.adminRegisterService(admin);
	}
	
	// ADMIN
	// REJECTING THE LOAN APPLICATION 
	// http://localhost:9091/VehicleLoanApp/users/Admin/Reject/{email}/{chassisNo}
	@PutMapping("/Admin/Reject/{email}/{chassisNo}")
	public void rejectApplication(@PathVariable String email, @PathVariable String chassisNo)
	{
		LoanAppTable loanapp = service.getLoanApplicationByChassis(chassisNo);
		loanapp.setStatus("REJECTED");
		service.modifyStatus(loanapp);
	}
	
	// ADMIN
	// APPROVING THE LOAN APPLICATION
	// http://localhost:9091/VehicleLoanApp/users/Admin/Approve/{email}/{chassisNo}
	@PostMapping("/Admin/Approve/{email}/{chassisNo}")
	public void ApproveApplication(@PathVariable String email, @PathVariable String chassisNo, @RequestBody Approved approved)
	{
		LoanAppTable l = service.getLoanApplicationByChassis(chassisNo);
		if(l.getStatus().contentEquals("PENDING"))
		{
			l.setStatus("APPROVED");
			service.modifyStatus(l);
			approved.setEmi(service.EMICalculate(l.getAmount(), l.getTenure(), l.getInterest()));
			approved.setLoanapp(l);
			service.AddApprovedDetails(approved);
		}
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ADMIN
	// VIEW ALL REGISTERED USERS
	// http://localhost:9091/VehicleLoanApp/users/Admin/
	@GetMapping("/Admin")
	public List<UserBasic> getAllUsersRegistrationDetails()
	{
		return service.findAllUserRegistrationDetails();
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// USER
	// REGISTER
	// http://localhost:9091/VehicleLoanApp/users/RegisterUser
	@PostMapping("/RegisterUser")
	public void addUser(@RequestBody UserBasic userbasic)
	{
		if(mail.mailValidate(userbasic.getEmail()))
		{
			mail.send(userbasic.getEmail(),"REGISTRATION SUCCESSFULL","<b>CONGRATULATIONS !</b> You have Successfully Registered with Wheels4Hope<br><p>Hope We will serve you better</p>");
			service.UserRegisterService(userbasic);
		}
	}
	
	// USER
	// APPLY FOR LOAN
	// http://localhost:9091/VehicleLoanApp/users/ApplyLoanDetails/{email}
	@PostMapping("/ApplyLoanDetails/{email}")
	public void addLoanDetails(@PathVariable String email, @RequestBody LoanAppTable loanapp)
	{
		UserBasic ub = service.getUserRegistrationdetails(email);
		if(ub.getUserdetails() != null)
		{ 
			service.applyVehicleLoan(loanapp, ub, ub.getUserdetails());
		}
		else
		{
			service.applyVehicleLoan(loanapp, ub, loanapp.getUser());
		}
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// USER
	// FORGOT PASSWORD
	// http://localhost:9091/VehicleLoanApp/users/ForgotPassword/GetOtp/{email}
	@GetMapping("/ForgotPassword/GetOtp/{email}")
	public String getOtp(@PathVariable String email)
	{
		String cotp = service.generateOTP();
		mail.send(email, "OTP For Password Regeneration", "<p>Your <b>One Time Password</b> is : "+cotp+"</p>");
		return cotp;
	}
	
	// USER
	// PASSWORD RESET
	// http://localhost:9091/VehicleLoanApp/users/ResetPassword/{email}/{password}
	@PutMapping("/ResetPassword/{email}/{password}")
	public void resetPassword(@PathVariable String email, @PathVariable String password)
	{
		UserBasic u = service.getUserRegistrationdetails(email);
		u.setPassword(password);
		service.resetPasswordService(u);
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// USER
	// VIEW REGISTRATION DETAILS
	// http://localhost:9091/VehicleLoanApp/users/ViewUserRegistrationDetails/{email}
	@GetMapping("/ViewUserRegistrationDetails/{email}")
	public UserBasic viewUserRegistrationDetails(@PathVariable String email)
	{
		return service.getUserRegistrationdetails(email);
	}
	
	// USER
	// VIEW USER DETAILS
	// http://localhost:9091/VehicleLoanApp/users/ViewUserDetails/{email}
	@GetMapping("/ViewUserDetails/{email}")
	public UserAdvanced viewUserDetails(@PathVariable String email)
	{
		return service.getUserDetailsService(email);
	}
	
	// USER
	// VIEW LOAN APPLICATION DETAILS
	// http://localhost:9091/VehicleLoanApp/users/LoanApplicationDetails/{email}
	@GetMapping("/LoanApplicationDetails/{email}")
	public List<LoanAppTable> getAllLoanApplication(@PathVariable String email)
	{
		List<LoanAppTable> list = service.getAllLoanApplication(email);
		return list;
	}


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	



}
