package com.ats.mahindrabattery.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

public class SwaggerConfig {
   
	 public static final String AUTHORIZATION_HEADER="Authorization";
	 
	 
	  @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2) 
	         .apiInfo(getInfo())
	         .securityContexts(securityContexts())
	         .securitySchemes(Arrays.asList(apikeys()))
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	  
	  private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
		  
	  }
	  
	  private List<SecurityReference> sf(){
		  AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		  return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] { scope }));
	  }
	  
	  
	  private ApiKey apikeys() {
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
		  
	  }
	  private ApiInfo getInfo() {
	         return new ApiInfo(
	           "Mahindra Electric", 
	           "Some custom description of API.", 
	           "API TOS", 
	           "Terms of service", 
	           new Contact("Mahindra Electric : Application", "ht", "myeaddress@company.com"), 
	           "License of API", "API license URL", Collections.emptyList());
	    }

	  

}
