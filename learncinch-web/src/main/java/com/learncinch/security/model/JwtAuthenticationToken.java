package com.learncinch.security.model;

import java.util.Collection;
import java.util.List;

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
	private static final long serialVersionUID = 5901601514186491758L;
	
	private JwtToken rawAccessToken;
	private UserContext userContext;
	
    public JwtAuthenticationToken(RawAccessJwtToken token) {
        super(null);
        this.rawAccessToken = token;
        this.setAuthenticated(false);
    }
    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }
    

	
	@Override
	public Object getCredentials() {
		return rawAccessToken;
	}

	@Override
	public Object getPrincipal() {
		return userContext;
	}

}
