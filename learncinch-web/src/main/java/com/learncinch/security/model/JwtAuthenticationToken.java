package com.learncinch.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


/**
 * An {@link org.springframework.security.core.Authentication} implementation
 * that is designed for simple presentation of JwtToken.
 * 
 * @author Imran Khan
 *
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private JwtToken rawAccessToken;
    public JwtAuthenticationToken(RawAccessJwtToken token) {
        super(null);
        this.rawAccessToken = token;
        this.setAuthenticated(false);
    }
	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

}
