package com.learncinch.security.auth.ajax;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to be used for AJAX based Login authentication.
 * @author Imran
 */

public class LoginRequest implements Serializable{
	
	private static final long serialVersionUID = 1596882929179226170L;
	private String username;
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("otp") String otp) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
