package com.yyc.bunnyroom.security.config;


import com.yyc.bunnyroom.security.config.handler.AuthFailHandler;
import com.yyc.bunnyroom.common.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
            .requestCache(request ->
                    request.requestCache(requestCache))
            .authorizeHttpRequests(auth -> { // 서버의 리소스에 접근 가능한 권한을 설정함
                //여기부터 로그인 권한을 설정하는 공간
                auth.requestMatchers("/inquirys","/security/auth/*","/image/**", "/security/auth/login", "/signup/*", "/main/**","/check/**", "/mailSend", "/mailAuthCheck", "/search/**", "/main/test2", "/").permitAll();
                auth.requestMatchers("/roomRegister/**").hasAnyAuthority(UserRole.HOST.getRole());
                auth.requestMatchers("/reservation/**").hasAnyAuthority(UserRole.GUEST.getRole());
                auth.requestMatchers("/myPage/**").hasAnyAuthority(UserRole.GUEST.getRole(),UserRole.HOST.getRole());
                auth.requestMatchers("/*/*").hasAnyAuthority(UserRole.ADMIN.getRole());
                auth.anyRequest().authenticated();

            })
            .formLogin(login ->{
                login.loginPage("/security/auth/login").permitAll(); // 로그인 페이지에 해당되는 서블릿이 존재해야 한다.

                // 로그인 아이디로 회원 이메일을 받음
                login.usernameParameter("userEmail");

                // 로그인 비밀번호로 회원 비밀번호를 받음
                login.passwordParameter("userPassword");

                // 로그인 성공시 메인 페이지로 이동
                login.defaultSuccessUrl("/main");

                login.failureHandler(authFailHandler);
            })
            .logout(logout ->{

                logout.logoutRequestMatcher(new AntPathRequestMatcher("/security/auth/logout"));
                logout.deleteCookies("JSESSIONID");
                logout.invalidateHttpSession(true);// 세션을 소멸하도록 허용하는 것


                // 로그아웃시 메인 페이지로 이동
                logout.logoutSuccessUrl("/main");


            })
            .sessionManagement(session ->{
                // session의 허용 개수를 제한
                session.maximumSessions(1);

                // 세션만료시 메인 페이지로 이동
                session.invalidSessionUrl("/main");

            }).csrf(csrf -> csrf.disable())

            .exceptionHandling(exceptionHandling ->
                    exceptionHandling.accessDeniedHandler(accessDeniedHandler())
            );

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // 접근 거부 처리 로직
            String message = "해당 페이지에 대한 권한이 없습니다.";
            System.out.println("AccessDenied:  "+message);

            // 메시지를 URL 파라미터로 전달하여 리다이렉트
            response.sendRedirect("/main?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8));
        };
    }
}
