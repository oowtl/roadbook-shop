package com.shop.shop2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //  SpringSecurityFilterChain 이 자동 포함된다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
//                .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
//                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
//                        .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated())
                .formLogin(formLoginCustomizer -> formLoginCustomizer
                                .loginPage("/members/login") // 로그인 페이지 url 설정
                                .defaultSuccessUrl("/") // 로그인 성공 시 이동 url
                                .usernameParameter("email") // 로그인 성공 시 사용할 파라미터 타입 설정
                                .failureUrl("/members/login/error") // 로그인 실패 시 이동 url
//                        .failureHandler(new CustomAuthenticationFailureHandler())
                ).logout(logoutCustomizer -> logoutCustomizer
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 url 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 url 설정

                )
                .build();

    }

    // 비밀번호를 암호화하기 위한 PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
