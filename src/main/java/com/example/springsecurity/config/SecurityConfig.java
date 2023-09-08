package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가(SecurityConfig로 등록할 필터) 스프링 필터체인(기본 필터 체인)에 등록이 된다
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/manager/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') && hasRole('MANAGER')"))
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.loginPage("/login").permitAll()); // 권한이 없는 page에 접속하려는 경우 /login페이지로 redirect

        return http.build();
    }

}
