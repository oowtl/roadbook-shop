package com.shop.shop2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // WebSecurityConfigurerAdapter 를 상속받는 클래스에 추가하면 SpringSecurityFilterChain 이 자동 포함된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // WebSecurityConfigurerAdapter의 오버라이드 메소드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    // 비밀번호를 암호화하기 위한 PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
