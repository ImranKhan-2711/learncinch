/**
 * 
 */
package com.learncinch.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncinch.dao.AccountMasterRepository;
import com.learncinch.dto.AccountDto;
import com.learncinch.model.Account;
import com.learncinch.service.AccountMasterService;

/**
 * 
 * @author Mandeep
 *
 */
@Service("accountMasterService")
public class AccountMasterServiceImpl extends ServiceBaseImpl<Account> implements AccountMasterService {

	@Autowired
	AccountMasterRepository accountMasterRepository;

	/**
	 * Sets the user master repository.
	 *
	 * @param userMasterRepository
	 *            the new user master repository
	 */
	@Autowired
	public void setAccountMasterRepository(AccountMasterRepository accountMasterRepository) {
		this.accountMasterRepository = accountMasterRepository;
		genericRepository = accountMasterRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.veridian.dynamics.UserMasterService#findByUserId(java.lang.String)
	 */
	@Override
	public AccountDto findByUserName(String userName) {
		Account a = accountMasterRepository.findByUserName(userName);
		AccountDto accountDto = new AccountDto(a.getAccountId(), a.getRoleId(), a.getUserName(), a.getPassword(),
				a.getStatus());
		return accountDto;
	}

}
