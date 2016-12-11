package com.mirae;

import javax.annotation.PostConstruct
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.mirae.config.PropertiesInitializr
import com.mirae.test.dao.TestDAO;

@SpringBootApplication
public class MiraeApplication {

    static final Logger log = LoggerFactory.getLogger(MiraeApplication.class);

    @Inject
    Environment env;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MiraeApplication.class)

		PropertiesInitializr.addDefaultProfile(app)
		Environment env = app.run(args).getEnvironment()
		log.info(
		"""
        ---------------------------------------------------------------
			PropertyMode : ${Arrays.toString(env.getActiveProfiles())}
            '${env.getProperty("spring.application.name")}' 가 시작되었습니다. Access URLs:
            Local: http://127.0.0.1:${env.getProperty("server.port")}
            External: http://${InetAddress.getLocalHost().getHostAddress()}:${env.getProperty("server.port")}
        ---------------------------------------------------------------
        """)
	}


	@Bean
	public CommandLineRunner runOnStartUp(TestDAO dao) {
		return {
			println dao.selectList(null);
		}
	}

}
