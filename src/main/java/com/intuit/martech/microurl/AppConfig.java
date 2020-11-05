package com.intuit.martech.microurl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	
	private String platform;
	private String url;
	private String driverClassName;
	private String username;
	private String password;
	
	@Bean
	@Profile("dev")
        public void devDataBaseConnection() {
		log.debug("setting Dev DB connection");
        }
	
	@Bean
	@Profile("prod")
	 public void prodDataBaseConnection() {
		log.debug("setting Dev DB connection");
        }

	@Bean
	@Profile("test")
	 public void testDataBaseConnection() {
		log.debug("setting Dev DB connection");
        }
	
}
