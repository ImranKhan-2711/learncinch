package com.learncinch.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.learncinch.LearncinchApplication;

/**
 * TO run the application from a tradition war file
 * @author Imran
 *
 */
public class ServletInitializer extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LearncinchApplication.class);
	}

}
