//package com.ats.mahindraelectric.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	
//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorizeRequests ->
//                authorizeRequests
////                        .antMatchers("/").permitAll()
//                        .antMatchers("/login/**")
//                        .access("isAuthenticated() and hasIpAddress('192.168.11.10')")
//                        .anyRequest().authenticated())
//            .formLogin(formLogin ->
//                formLogin
//                    .permitAll())
//            .csrf().disable();
//        
//        return http.build();
//    }
//	
//	
//}
//package com;


