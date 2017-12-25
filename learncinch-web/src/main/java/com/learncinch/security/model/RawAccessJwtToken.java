package com.learncinch.security.model;

/**
 * Represents the JWT token(just the payload itself)
 * @author Imran Khan
 *
 */
public class RawAccessJwtToken implements JwtToken {

	private String token;

	public RawAccessJwtToken(String token) {
		this.token = token;
	}
	
	@Override
	public String getToken() {
		return token;
	}

}
