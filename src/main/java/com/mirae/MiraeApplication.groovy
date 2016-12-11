package com.mirae;

import javax.inject.Inject

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.core.env.Environment

import com.mirae.config.MiraeProperties
import com.mirae.config.PropertiesInitializr
import com.mirae.test.dao.TestDAO

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class])
@ComponentScan(basePackages = ["com.mirae"], excludeFilters=[@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.mirae.test.*")])
public class MiraeApplication {

    static final Logger log = LoggerFactory.getLogger(MiraeApplication.class);

    @Inject
    Environment env


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MiraeApplication.class)

		PropertiesInitializr.addDefaultProfile(app)

		ApplicationContext ctx = app.run(args);
		Environment env = ctx.getEnvironment()

		log.info(
		"""
        ---------------------------------------------------------------
			PropertyMode : ${Arrays.toString(env.getActiveProfiles())}
            '${env.getProperty("spring.application.name")}' 가 시작되었습니다. Access URLs:
            Local: http://127.0.0.1:${env.getProperty("server.port")}
            External: http://${InetAddress.getLocalHost().getHostAddress()}:${env.getProperty("server.port")}
        ---------------------------------------------------------------
        """)

		String[] beanNames = ctx.getBeanDefinitionNames()
		Arrays.sort(beanNames)
		println "**************** Loaded Beans List ****************"
		beanNames.each {
			println it
		}
	}

	@Bean
	public CommandLineRunner runOnStartUp(MiraeProperties prop) {
		return {
			println prop.mode

			//println dao.selectList(null);
		}
	}

}
