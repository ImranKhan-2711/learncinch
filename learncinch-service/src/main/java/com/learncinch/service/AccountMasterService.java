/**
 * 
 */
package com.learncinch.service;

import com.learncinch.dto.AccountDto;
import com.learncinch.model.Account;

/**
 * 
 * @author Mandeep
 *
 */
public interface AccountMasterService extends BaseService<Account> {

	AccountDto findByUserName(String userName);

}
