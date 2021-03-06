package com.learncinch.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncinch.security.auth.ajax.AjaxAuthenticationProvider;
import com.learncinch.security.auth.ajax.AjaxAwareAuthenticationFailureHandler;
import com.learncinch.security.auth.ajax.AjaxAwareAuthenticationSuccessHandler;
import com.learncinch.security.auth.ajax.AjaxLoginProcessingFilter;
import com.learncinch.security.auth.jwt.JwtAuthenticationProvider;
import com.learncinch.security.auth.jwt.JwtTokenAuthenticationFilter;
import com.learncinch.security.auth.jwt.SkipPathRequestMatcher;
import com.learncinch.security.jwt.tokenExtractor.TokenExtractor;

/**
 * Class to configure spring security User @EnableWebSecurity to enforce
 * security
 * 
 * @author Imran
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
	// API Access points should be somewhat like these
	public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
	public static final String FORM_BASED_REGISTRATION_ENTRY_POINT = "/api/auth/registration";
	
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";
	private static final String[] BY_PASS_SECURITY_PERMITALL_URLS = { "/","/resources/**", FORM_BASED_REGISTRATION_ENTRY_POINT,FORM_BASED_LOGIN_ENTRY_POINT,TOKEN_REFRESH_ENTRY_POINT};
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AjaxAuthenticationProvider ajaxAuthenticationProvider;
	
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	private AjaxAwareAuthenticationFailureHandler ajaxAwareAuthenticationFailureHandler;
	
	@Autowired
	private AjaxAwareAuthenticationSuccessHandler ajaxAwareAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenExtractor tokenExtractor;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(BY_PASS_SECURITY_PERMITALL_URLS).permitAll()
		//.and().formLogin().loginPage("/login").permitAll() //This would be removed once we recieve a hit through API login access point
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests().antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
		.and().addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class) // Protected API End points Authenticated here
		.addFilterBefore(buildJwtTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(ajaxAuthenticationProvider);
		auth.authenticationProvider(jwtAuthenticationProvider);
	}
	
	/**
	 * The filter to be called before <code>UsernamePasswordAuthenticationFilter</code> filters a request
	 * This filter is to be called only on login
	 * @return
	 * @throws Exception
	 */
	protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
		AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, ajaxAwareAuthenticationSuccessHandler,
				ajaxAwareAuthenticationFailureHandler, objectMapper);
		filter.setAuthenticationManager(this.authenticationManager);
		return filter;
	}
	
	/**
	 * The filter to be called before <code>UsernamePasswordAuthenticationFilter</code> filters a request
	 * It skips request from token refresh page
	 * It is mandatory to check the token on every other request(i.e TOKEN_BASED_AUTH_ENTRY_POINT)
	 * @return
	 * @throws Exception
	 */
	protected JwtTokenAuthenticationFilter buildJwtTokenProcessingFilter() throws Exception {
		List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT);
		SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
		JwtTokenAuthenticationFilter filter = new JwtTokenAuthenticationFilter(ajaxAwareAuthenticationFailureHandler,
				tokenExtractor, matcher);
		filter.setAuthenticationManager(this.authenticationManager);
		return filter;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/resources/**"); // #excluding resources
    }

}
