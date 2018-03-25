package com.learncinch.security.auth.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {

	 public String createAccessJwtToken(Object principal, List authorities) {
	    if (StringUtils.isBlank(principal.toString())) 
	        throw new IllegalArgumentException("Cannot create JWT Token without username");
	
	    if (authorities == null || authorities.toString().isEmpty()) 
	        throw new IllegalArgumentException("User doesn't have any privileges");
	    Claims claims = Jwts.claims();
	    claims.setSubject(principal.toString());
	    
	    claims.put("scopes", authorities.stream().map(s -> s.toString()).collect(Collectors.toList()));
	    
	    String token = Jwts.builder()
	            .setClaims(claims)
	            /*.setIssuer(settings.getTokenIssuer())*/
	            .setIssuer("Imran")//We'll use a Unique string here defined in settings later
	            .setIssuedAt(Date.from(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC)))
	            .setExpiration(Date.from(LocalDateTime.now()
	            	.plusMinutes(10)//We'll use a Unique string here defined in settings later
	                /*.plusMinutes(settings.getTokenExpirationTime())*/
	                .atZone(ZoneId.systemDefault()).toInstant()))//We'll use a Unique string here defined in settings later
	           /* .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())*/
	            .signWith(SignatureAlgorithm.HS512, "ABCD123")
	          .compact();
	    
		return token;
	}
}
