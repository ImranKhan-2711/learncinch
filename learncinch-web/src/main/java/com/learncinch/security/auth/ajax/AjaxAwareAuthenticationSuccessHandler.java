package com.learncinch.security.auth.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncinch.security.auth.jwt.JwtTokenFactory;
/**
 * Success Handler For AjaxAware AuthenticationFilter
 * @author Mandeep
 *
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	JwtTokenFactory tokenFactory;
	
	@Autowired
	ObjectMapper objectmapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Authentication success");

		Object principal = authentication.getPrincipal();
		List authorities =(List) authentication.getAuthorities();
		//JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
		String accessToken = tokenFactory.createAccessJwtToken(principal, authorities);
		//If needed RefreshToken could also be created and added to the map below
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("token", accessToken);
	        
		objectmapper.writeValue(response.getWriter(), tokenMap);
	}

}
