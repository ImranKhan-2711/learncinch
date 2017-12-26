package com.learncinch.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.learncinch.security.model.JwtAuthenticationToken;
import com.learncinch.security.model.RawAccessJwtToken;
import com.learncinch.security.model.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Authentication provider to verify Jwt token on each request
 * @author Imran
 *
 */

@Component	
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
		
		Jws<Claims> jwsClaims = rawAccessToken.parseClaims("ABCD123");
		String subject = jwsClaims.getBody().getSubject();
		List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
		List<GrantedAuthority> authorities = scopes.stream()
				.map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());
		UserContext context = UserContext.create(subject, authorities);
		return new JwtAuthenticationToken(context, context.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
