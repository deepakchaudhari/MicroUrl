package com.intuit.martech.microurl.web.rest.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages="com.intuit.martech.microurl.web.rest")
public class ApplicationSwaggerConfig {

   @Bean
   public Docket customDocket(){
      return new Docket(DocumentationType.SWAGGER_2)
    		  .select()
              .apis(RequestHandlerSelectors.any())
              .paths(PathSelectors.ant("/api/**"))
              .build()
              .apiInfo(getApiInfo());
   }

   private ApiInfo getApiInfo() {
	   return new ApiInfo(
		"MicroUrl API Documentation",
		"This is REST API documentation for the MicroUrl( URL-shortener service)",
		"1.0",
		"Terms of service",
		new Contact(
				"Deepak Chaudhari",
				"https://github.intuit.com/dchaudhari/microurl.git",
				"deepakkumar_chaudhari@intuit.com"),
		"1.0",
		"LICENSE-1.0");
   }


}
