package com.learncinch.security.auth.ajax;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * THe Authentication Provider to be called along with AjaxLoginProcessingFilter
 * @author Imran
 *
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider{
	 private static Logger logger = LoggerFactory.getLogger(AjaxAuthenticationProvider.class);
	 
	 @Autowired
	 private BCryptPasswordEncoder encoder;
	 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication!=null) logger.info("PasswordEncoder bean successfully initiated ");;
    	if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {			
			throw new IllegalArgumentException("Only CustomAuthenticationManager is supported");
		}

    	UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        
        String username = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        //User user = userService.findByUserName(username);
          UserDTO user = getUserByUserName(username);
        if (user==null || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }
        
        //Fetch roles(authorities) 
       /*List<RoleDTO> roles = userService.findRoleByUserId(user.getUserId());
        
        if (user.getUserRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                .collect(Collectors.toList());*/
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), authorities);
	}
	
	//Keep it in service linked with repository
	public UserDTO getUserByUserName(String userName) {
		UserDTO user = null;
		if (userName.equals("mandeep")) {
			user = new UserDTO(1l, "mandeep", "$2a$10$T3t6RdRjcEwR44T775jsP.NvbgSKMi7x9r1ORQA9gnx86GE6JDfQe");
		} else if (userName.equals("harman")) {
			user = new UserDTO(2l, "harman", "$2a$10$T3t6RdRjcEwR44T775jsP.NvbgSKMi7x9r1ORQA9gnx86GE6JDfQe");
		}else if (userName.equals("imran")) {
			user = new UserDTO(3l, "imran", "$2a$10$T3t6RdRjcEwR44T775jsP.NvbgSKMi7x9r1ORQA9gnx86GE6JDfQe");
		}

		return user;
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
