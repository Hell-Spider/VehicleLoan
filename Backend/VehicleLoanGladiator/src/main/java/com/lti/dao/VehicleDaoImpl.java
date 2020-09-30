package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lti.model.Account;
import com.lti.model.AdminDetails;
import com.lti.model.Approved;
import com.lti.model.LoanAppTable;
import com.lti.model.UserAdvanced;
import com.lti.model.UserBasic;

@Repository
public class VehicleDaoImpl implements VehicleDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// ADMIN
	// REGISTER
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void adminRegister(AdminDetails admin) {
		entityManager.persist(admin);
	}
	
	// ADMIN
	// MODIFY
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void modifyLoanApplicationStatus(LoanAppTable loanapp) {
		entityManager.merge(loanapp);
	}
	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public void ApprovedLoan(Approved approved) {
		entityManager.persist(approved);
	}
	
	// ADMIN 
	// VIEW
	@Override
	public List<UserBasic> showAllUserRegistrationDetails() {
		String jpql = "select u from UserBasic u";
		TypedQuery<UserBasic> tquery = entityManager.createQuery(jpql,UserBasic.class);
		return tquery.getResultList();
	}
	@Override
	public Account getAccountByEmail(String email) {
		String jpql = "select a from Account a where a.user.userId = (select u.userId from UserAdavanced u where u.userregister.email=:email";
		Query query = entityManager.createQuery(jpql).setParameter("email", email);
		return (Account)query.getSingleResult();
	}
	
	
	// USER
	// REGISTER
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void userRegister(UserBasic userbasic) {
		entityManager.persist(userbasic);
	}
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void ApplyLoan(LoanAppTable loanapp) {
		entityManager.persist(loanapp);
	}

	
	
	// USER
	// MODIFY
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void passwordReset(UserBasic userbasic) {
		entityManager.merge(userbasic);
	}

	
	// USER
	// VIEW
	@Override
	public UserBasic showUserRegistrationInformation(String email) {
		UserBasic userbasic = entityManager.find(UserBasic.class, email);
		return userbasic;
	}
	@Override
	public UserAdvanced showUserDetailsInformation(String email) {
		String jpql = "select u from UserAdvanced u where u.userregister.email=:email";
		Query query = entityManager.createQuery(jpql).setParameter("email",email);
		UserAdvanced u = (UserAdvanced)query.getSingleResult();
		return u;
	}
	@Override
	public List<LoanAppTable> showAllLoanApplication(String email) {
		String jpql = "select l from LoanAppTable l where l.user.userId=(select u.userId from UserAdvanced u where u.userregister.email=:email)";
		TypedQuery<LoanAppTable> tquery = entityManager.createQuery(jpql,LoanAppTable.class).setParameter("email", email);
		List<LoanAppTable> list = tquery.getResultList();
		return list;
	}
	@Override
	public LoanAppTable showLoanApplicationByChassis(String chassisNo) {
		return entityManager.find(LoanAppTable.class, chassisNo);
	}









}
