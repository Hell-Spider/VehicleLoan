package com.lti.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.EmiClass;
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
	@PostMapping("/RegisterAdmin")
	public void addAdmin(@RequestBody AdminDetails admin)
	{
		service.adminRegisterService(admin);
	}
	
	// ADMIN
	// REJECTING THE LOAN APPLICATION 
	// http://localhost:9091/VehicleLoanApp/users/Admin/Reject/{email}
	@PutMapping("/Admin/Reject/{email}")
	public void rejectApplication(@PathVariable String email, @RequestBody LoanAppTable loanapp)
	{
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
			UserAdvanced u = service.getUserDetailsService(email);
			
			if(u.getAccount()==null)
			{
				l.setStatus("APPROVED");
				service.modifyStatus(l);
				approved.setAccount(new Account());
				approved.getAccount().setUser(u);
				approved.setEmi(service.EMICalculate(l.getAmount(), l.getTenure(), l.getInterest()));
				approved.setEmidate(new Date());
				approved.setLoanapp(l);
				service.AddApprovedDetails(approved);
			}
			else
			{
				l.setStatus("APPROVED");
				service.modifyStatus(l);
				approved.setAccount(u.getAccount());
				approved.setEmi(service.EMICalculate(l.getAmount(), l.getTenure(), l.getInterest()));
				approved.setEmidate(new Date());
				approved.setLoanapp(l);
				service.AddApprovedDetails(approved);
			}
			
		}
		else
		{
			System.out.println("Already Approved");
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
	
	// VIEW ALL THE REJECTED LOAN APPLICATION
	// http://localhost:9091/VehicleLoanApp/users/Admin/Rejected
	@GetMapping("/Admin/Rejected")
	public List<LoanAppTable> getAllRejectedLoanApplications()
	{
		return service.viewAllRejectedLoanApplications();
	}
	
	// VIEW ALL THE APPROVED LOAN APPLICATIONS
	// http://localhost:9091/VehicleLoanApp/users/Admin/Accepted
	@GetMapping("/Admin/Accepted")
	public List<LoanAppTable> getAllAcceptedLoanApplications()
	{
		return service.viewAllAcceptedLoanApplications();
	}
	
	// ADMIN DETAILS BY EMAIL
	// http://localhost:9091/VehicleLoanApp/users/ViewAdminRegistrationDetails/{email}
	@GetMapping("/ViewAdminRegistrationDetails/{email}")
	public AdminDetails viewAdminRegistrationDetails(@PathVariable String email)
	{
		return service.geAdminRegistrationdetails(email);
	}
	
	// ADMIN
	// VIEW ALL APPROVED USERS
	// http://localhost:9091/VehicleLoanApp/users/ViewApprovedUsers
	@GetMapping("/ViewApprovedUsers")
	public List<UserBasic> ApprovedUsers()
	{
		return service.viewAllApprovedUsers();
	}
	
	// ADMIN
	// VIEW ALL REJECTED USERS
	// http://localhost:9091/VehicleLoanApp/users/ViewRejectedUsers
	@GetMapping("/ViewRejectedUsers")
	public List<UserBasic> RejectedUsers()
	{
		return service.viewAllRejectedUsers();
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
	// http://localhost:9091/VehicleLoanApp/users/ResetPassword/{email}/
	@PutMapping("/ResetPassword/{email}")
	public void resetPassword(@PathVariable String email, @RequestBody UserBasic userbasic)
	{
		UserBasic u = service.getUserRegistrationdetails(email);
		u.setPassword(userbasic.getPassword());
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
	
	// USER
	// VIEW ALL APPROVED LOAN DETAILS
	// http://localhost:9091/VehicleLoanApp/users/ApprovedLoanDetails/{email}
	@GetMapping("/ApprovedLoanDetails/{email}")
	public List<Approved> getAllApproved(@PathVariable String email)
	{
		return service.viewAllApprovedByEmail(email);
	}
	
	// USER
	// VIEW ALL REJECTED LOAN DETAILS
	// http://localhost:9091/VehicleLoanApp/users/RejectedLoanDetails/{email}
	@GetMapping("/RejectedLoanDetails/{email}")
	public List<LoanAppTable> getAllRejected(@PathVariable String email)
	{
		return service.getAllRejectedByEmail(email);
	}

	// USER
	// VIEW THE EMI DETAILS 
	// http://localhost:9091/VehicleLoanApp/users/Approved/EMIList/{loanId}
	@GetMapping("/Approved/EMIList/{loanId}")
	public List<EmiClass> getEMIList(@PathVariable int loanId)
	{
		Approved ad = service.viewApprovedByLoanId(loanId);
		return service.calculateEmi(ad.getLoanapp().getAmount(), ad.getLoanapp().getTenure(), ad.getLoanapp().getInterest(),ad.getEmidate());
	}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// LOGIN SERVICE
	@PostMapping(path = "/login/user")
	public ResponseEntity<String> loginuser(@RequestBody LoginDto login) {

		boolean result = service.verifyUserLogin(login);
		if (result) {
			return ResponseEntity.ok("Login Success");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(path = "/login/admin")
	public ResponseEntity<String> loginadmin(@RequestBody LoginDto login) {
		boolean result = service.verifyAdminLogin(login);
		if (result) {
			return ResponseEntity.ok("Login Success");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
