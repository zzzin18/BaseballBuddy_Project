package com.example.BaseballBuddy1.config;

import com.example.BaseballBuddy1.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final MemberService memberService;  // UserDetailsService 구현체

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
            authBuilder.userDetailsService(memberService).passwordEncoder(passwordEncoder());
            return authBuilder.build();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/static/**", "/favicon.ico",
                                    "/members/join", "/registration", "/registration.html", "/login.html", "/index.html",
                                    "/members/check-id", "/members/check-email","/members/registration",
                                    "/h2-console/**", "/attendCalendar", "/attendCalendar.html",
                                    "/review", "/review.html", "/enums/stadium", "/enums/filterTag",
                                    "/review-posts", "/review-posts/filter", "/attend-posts/by-date", "/comments/by-post/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                    .formLogin(form -> form
                            .loginPage("/login.html")
                            .loginProcessingUrl("/members/login")
                            .usernameParameter("memberId")
                            .passwordParameter("memberPw")
                            .successHandler((request, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\": \"login success\"}");
                            })
                            .failureHandler((request, response, exception) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\": \"login failed\"}");
                            })
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/members/logout")
                            .logoutSuccessUrl("/login.html")
                            .permitAll()
                    )
                    .exceptionHandling(e -> e
                            .authenticationEntryPoint((request, response, authException) -> {
                                String uri = request.getRequestURI();
                                if (uri.startsWith("/members/me") || uri.startsWith("/review-posts")) {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                                } else {
                                    response.sendRedirect("/login.html");
                                }
                            })
                    );

            return http.build();
        }

/*
        //DB접속용 코드
        @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // 이거 추가!
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/", "/members/join", "/registration", "/registration.html", "/login.html", "/index.html",
                "/members/check-id", "/members/check-email",
                "/h2-console/**", "/attendCalendar", "/attendCalendar.html", "/review", "review.html",
                "/enums/stadium", "/enums/filterTag"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login.html")
            .loginProcessingUrl("/members/login")
            .usernameParameter("memberId")
            .passwordParameter("memberPw")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login.html?error")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/members/logout")
            .logoutSuccessUrl("/login.html")
            .permitAll()
        )
        .exceptionHandling(e -> e
            .authenticationEntryPoint((request, response, authException) -> {
                if (request.getRequestURI().equals("/members/me")) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    response.sendRedirect("/login.html");
                }
            })
        );

    return http.build();
}
*/



    }
