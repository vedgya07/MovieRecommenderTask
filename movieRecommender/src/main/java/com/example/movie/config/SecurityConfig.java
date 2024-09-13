package com.example.movie.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.movie.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/users/**").permitAll()
            .requestMatchers("/api/movies/**", "/api/genres/**", "/api/ratings/**").permitAll()
              // .requestMatchers("/api/users/**").hasRole("ADMIN")
               // .requestMatchers("/api/movies/**", "/api/genres/**", "/api/ratings/**").authenticated()
                .anyRequest().permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic(); // For simplicity, using HTTP Basic authentication for testing

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
       // return NoOpPasswordEncoder.getInstance(); // for no password encoding
    	return new BCryptPasswordEncoder();
    }
}