package com.learncinch.security.auth.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class SkipPathRequestMatcher implements RequestMatcher{

	@Override
	public boolean matches(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
