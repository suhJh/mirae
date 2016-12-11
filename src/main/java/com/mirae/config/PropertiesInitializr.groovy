package com.mirae.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication

import com.google.common.collect.Maps

class PropertiesInitializr {

		private static final long serialVersionUID = 1L

		private static final Logger log = LoggerFactory.getLogger(PropertiesInitializr.class)

		private static final String SPRING_PROFILE_ACTIVE = "spring.profiles.active"

		private static final Properties BUILD_PROPERTIES = readProperties()

		//  프로퍼티 파일의 spring.profiles.active 의 프로파일 식별자
		public static final String SPRING_PROFILE_DEVELOPMENT = "dev"
		public static final String SPRING_PROFILE_PRODUCTION = "prod"

		/**
		 * 어플리케이션 객체에 설정을 주입
		 */
		public static void addDefaultProfile(SpringApplication app) {
			def defProperties = Maps.newHashMap()
			defProperties.put(SPRING_PROFILE_ACTIVE, getDefaultActiveProfiles());
			app.setDefaultProperties(defProperties);
		}

		/**
		 * 프로퍼티에서 설정 내용물을 가져옴
		 */
		public static String getDefaultActiveProfiles(){
			if (BUILD_PROPERTIES) {
				String activeProfile = BUILD_PROPERTIES.getProperty(SPRING_PROFILE_ACTIVE)
				if (activeProfile && (activeProfile.contains(SPRING_PROFILE_DEVELOPMENT) || activeProfile.contains(SPRING_PROFILE_PRODUCTION))) {
					log.info("★APPLIED PROPERTY FILE ${activeProfile}★")
					return activeProfile;
				}
			}
			log.warn("설정된 프로퍼티가 없습니다. *개발 프로퍼티를 적용합니다. ${SPRING_PROFILE_DEVELOPMENT}")
			return SPRING_PROFILE_DEVELOPMENT;
		}


		/**
		 * 환경에 따른 프로퍼티를 읽는다.
		 */
		private static Properties readProperties() {
			try {
				log.info( '★READING PROPERTY FILE /config/application.properties★' )

				def props = new Properties()

				def props_url = getClass().getResource("/config/application.properties").path

				new File(props_url).withInputStream {
					stream -> props.load(stream)
				}

				return props

			} catch (Exception e) {
				log.error("기본 프로퍼티 파일을 읽을 수 없습니다.\n${e}");
			}
			return null;
		}
}
