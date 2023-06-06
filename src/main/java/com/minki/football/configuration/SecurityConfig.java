package com.minki.football.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용

//        http.csrf((csrf)->csrf.disable());
        http
                .authorizeHttpRequests(
                        (authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .requestMatchers("/", "/user/signup","/league/**","/team/**","/player/**","/static/js/**","/static/assets/img/**","/static/css/**","/static/images/**").permitAll()
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                );

        // login 설정
        http
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/user/login")
                                .loginProcessingUrl("/user/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/user/index",true)
                                .permitAll()
                );

        // logout 설정
        http
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/")
                                .permitAll()
                );

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication 로그인 , Authorization 권한
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("왓다");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                // 인증처리
                .usersByUsernameQuery("select username, password, true "
                        + "from member "
                        + "where username = ?")
                // 권한처리
                .authoritiesByUsernameQuery("select m.username, r.name "
                        + "from user_role ur inner join member m on ur.member_id=m.member_id "
                        + "inner join role r on ur.role_id=r.id "
                        + "where m.username = ?");
    }


}

