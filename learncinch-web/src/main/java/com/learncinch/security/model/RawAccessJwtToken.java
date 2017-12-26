package com.learncinch.security.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import com.learncinch.security.exception.CustomJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Represents the JWT token(just the payload itself)
 * @author Imran Khan
 *
 */
public class RawAccessJwtToken implements JwtToken {
	private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);
	
	private String token;

	public RawAccessJwtToken(String token) {
		this.token = token;
	}
	
	@Override
	public String getToken() {
		return token;
	}
	
	/**
     * Parses and validates JWT Token signature.
	 * @throws AuthenticationException 
     * 
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     * 
     */
    public Jws<Claims> parseClaims(String signingKey) throws AuthenticationException {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Invalid JWT Token", ex.getMessage());
            throw new CustomJwtException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx.getMessage());
            throw new CustomJwtException("JWT Token expired", expiredEx);
        }
    }

}
