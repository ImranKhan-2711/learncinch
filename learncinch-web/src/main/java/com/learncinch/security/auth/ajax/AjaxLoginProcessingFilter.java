package com.learncinch.security.auth.ajax;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Filter to be called for intercepting Login request
 * Note: This Filter will be called only once during login
 * @author Imran
 *
 */
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

	//See why in crypex we are using AuthenticationSuccessHandler directly instead of AjaxAwareAuthenticationSuccessHandler
	private final AjaxAwareAuthenticationSuccessHandler successHandler;
    private final AjaxAwareAuthenticationFailureHandler failureHandler;

    private final ObjectMapper objectMapper;
    

	public AjaxLoginProcessingFilter(String formBasedLoginEntryPoint, AjaxAwareAuthenticationSuccessHandler ajaxAwareAuthenticationSuccessHandler,
		AjaxAwareAuthenticationFailureHandler ajaxAwareAuthenticationFailureHandler, ObjectMapper objectMapper) {
		
		super(formBasedLoginEntryPoint);
		this.successHandler = ajaxAwareAuthenticationSuccessHandler;
		this.failureHandler = ajaxAwareAuthenticationFailureHandler;
		this.objectMapper = objectMapper;
	}

	/**
	 * Attempts Authentication based on username and password(Since we are using 
	 * <code>AbstractAuthenticationProcessingFilter</code>
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String json = "{\"username\":\"imran\",\r\n" + 
			    "\"password\":\"1\"\r\n" + 
			    "}";
		/*Gson gson = new Gson(); // Or use new GsonBuilder().create();
		LoginRequest target2 = gson.fromJson(request.getReader(), LoginRequest.class); */
		
		LoginRequest loginRequest = objectMapper.readValue(json, LoginRequest.class);
		request.getReader().lines().collect(java.util.stream.Collectors.joining());
		
		//LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        
        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
        return  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		
		
        
	}
	
	  @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	            Authentication authResult) throws IOException, ServletException {
	        successHandler.onAuthenticationSuccess(request, response, authResult);
	    }
	  
	  @Override
	    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	            AuthenticationException failed) throws IOException, ServletException {
	       // SecurityContextHolder.clearContext();
	        failureHandler.onAuthenticationFailure(request, response, failed);
	    }

}
