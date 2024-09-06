package com.ats.mahindrabattery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ats.mahindrabattery.entity.DashboardDetailsEntity;
import com.ats.mahindrabattery.serviceimpl.DashboardDetailsServiceImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableCaching
@EnableScheduling
public class AtsMahindraBatteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtsMahindraBatteryApplication.class, args);

	}

//	@Bean
//	public MultipartResolver multipartResolver() {
//	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//	    System.out.println("resolver::"+resolver);
//	    resolver.setMaxUploadSize(1048576); // 1 MB in bytes
//	    return resolver;
//	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				// registry.addMapping("/**").allowedOrigins("http://192.168.1.100:8080");


		//	registry.addMapping("/**").allowedOrigins("http://192.168.11.173:4201","http://192.168.11.173:8081","http://localhost:8081","http://localhost:4201");
			//	registry.addMapping("/**").allowedOrigins("http://localhost:4201,"http://192.168.183.89:8080","http://192.168.183.89:4201");

				registry.addMapping("/**").allowedOrigins("http://192.168.11.173:4200", "http://192.168.11.173:8083",
						"http://localhost:8083", "http://localhost:4200", "http://192.168.11.107:8000",
						"http://192.168.11.107:8080", "http://192.168.11.38:8080", "http://192.168.11.102:8080",
						"http://192.168.11.119:8080","http://192.168.10.235:8080","http://192.168.10.121:8080","http://192.168.11.185:8000");
				// registry.addMapping("/**").allowedOrigins("http://localhost:4201");

			}
		};
	}
}
