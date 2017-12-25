package com.learncinch.security.jwt.tokenExtractor;

/**
 * Implementations of this interface should always return raw base-64 encoded
 * representation of JWT Token.
 * 
 * @author Imran Khan
 *
 */
public interface TokenExtractor {

public String extract(String payload);	
}
