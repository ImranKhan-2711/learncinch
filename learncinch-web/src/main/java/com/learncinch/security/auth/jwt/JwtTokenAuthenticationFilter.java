package com.learncinch.security.auth.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.learncinch.security.jwt.tokenExtractor.TokenExtractor;

/**
 * This filter is used for validation of  
 * @author Imran Khan
 */
public class JwtTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
	//This constructor would be defined once we have a matcher	
	 @Autowired
	    public JwtTokenAuthenticationFilter(AuthenticationFailureHandler failureHandler, 
	            TokenExtractor tokenExtractor, RequestMatcher matcher) {
	        super(matcher);
	        this.failureHandler = failureHandler;
	        this.tokenExtractor = tokenExtractor;
	    }
	 
	 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
