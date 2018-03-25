package com.learncinch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The class Account
 * 
 * @author Mandeep
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
	private Long accountId;
	private String roleId;
	private String userName;
	private String password;
	private String status;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Long accountId) {
		super();
		this.accountId = accountId;
	}

	@Id
	@Column(name = "ACCOUNT_ID", nullable = false, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
	@SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ", allocationSize = 1, initialValue = 1)
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@JoinColumn(name = "ROLE_ID", nullable = false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleMaster) {
		this.roleId = roleMaster;
	}

	@Column(name = "USERNAME", length = 255, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", length = 255, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
