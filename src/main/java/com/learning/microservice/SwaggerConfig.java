package com.learning.microservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact MY_DEFAULT_CONTACT = new Contact("Palanivel", "http:palanivel.com", "pv@gmail.com");
	public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation for Book api service", "Api Documentation", "1.0", "urn:tos",
			  MY_DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_PRODC_CONSUMES = new HashSet<String>(java.util.Arrays.asList("application/xml","application/json"));


	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT).produces(DEFAULT_PRODC_CONSUMES);
	}

}
