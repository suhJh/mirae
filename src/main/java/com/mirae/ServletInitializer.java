package com.mirae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.mirae.config.PropertiesInitializr;

public class ServletInitializer extends SpringBootServletInitializer {

	static final Logger log = LoggerFactory.getLogger(ServletInitializer.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		log.info("**initializer starts**");

		PropertiesInitializr.addDefaultProfile(application.application());
		return application.sources(MiraeApplication.class);
	}


}
