package com.learncinch.security.exception;

import org.springframework.security.core.AuthenticationException;
/**
 * Handle JWT related exceptions
 * @author Imran Khan
 *
 */
public class CustomJwtException extends AuthenticationException {

	private static final long serialVersionUID = -1107403142417683506L;

	public CustomJwtException(String msg) {
		super(msg);
	}
	
	public CustomJwtException(String msg, Throwable t) {
		super(msg, t);
	}

}
