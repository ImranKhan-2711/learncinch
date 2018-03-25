package com.learncinch.dao;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learncinch.model.Account;
/**
 * 
 * @author Mandeep
 *
 */
@Repository
@Transactional
public interface AccountMasterRepository extends GenericRepository<Account, Long> {

	Account findByUserName(@Param("userName") String userName);

}
