package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가(SecurityConfig로 등록할 필터) 스프링 필터체인(기본 필터 체인)에 등록이 된다
@EnableGlobalAuthentication
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/user/**").authenticated() // 인증만되면 들어갈 수 있는 주소!
                        .requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers( "/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login") // /login으로 접근하면 security가 낚아채서 login을 진행함
                        .defaultSuccessUrl("/") // 만약, loginForm을 요청한 페이지가 /user라면 권한이 있는한 로그인 성공시 /user로 바로 이동함
                ); // 권한이 없는 page에 접속하려는 경우 /login페이지로 redirect


        return http.build();
    }

    // 해당 메소드가 리턴하는 오브젝트가 Bean으로 등록됨
    @Bean
    public BCryptPasswordEncoder encryptPwd() {
        return new BCryptPasswordEncoder();
    }

}
