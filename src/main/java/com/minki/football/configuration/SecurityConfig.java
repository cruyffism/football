package com.minki.football.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http
                .authorizeRequests()
                .requestMatchers("/user/login", "/user/signup").permitAll()
                .anyRequest().authenticated();

        // login 설정
        http
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("id") // login에 필요한 id 값을 email로 설정 (default는 username)
                                .passwordParameter("password") // login에 필요한 password 값을 password(default)로 설정
                                .loginPage("/user/login") // GET 요청 (login form을 보여줌)
//                                .failureUrl("/authentication/login?failed")
                                .defaultSuccessUrl("/") // login에 성공하면 /로 redirect
                                .loginProcessingUrl("/auth")); // POST 요청 (login 창에 입력한 데이터를 처리)

        // logout 설정
        http
                .logout((logout) ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")// logout에 성공하면 /로 redirect
                );

        return http.build();
    }
}

