package com.sparta.nyangdangback.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.nyangdangback.jwt.JwtAuthFilter;
import com.sparta.nyangdangback.jwt.JwtUtil;
import com.sparta.nyangdangback.user.repository.UserRepository;
import com.sparta.nyangdangback.util.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // ????????? Security ????????? ???????????? ???
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured ??????????????? ?????????
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;
    private final ObjectMapper om;
    private final UserRepository userRepository;
    //
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console ?????? ??? resources ?????? ?????? ??????
        return (web) -> web.ignoring()
                //.requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF ??????
        http.csrf().disable();
        // ?????? ????????? Session ????????? ???????????? ?????? JWT ????????? ???????????? ?????? ??????
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/blogs/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/logout").permitAll()
                .antMatchers("/api/reply/**").permitAll()
                .anyRequest().authenticated()
                // JWT ??????/????????? ???????????? ?????? ??????
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); //    private final JwtUtil jwtUtil; ????????????!
        http.cors();
        // ????????? ??????
        http.formLogin().permitAll();// ????????? ???????????? ?????? ?????? ??????!.loginPage(".api/user/login-page").permitAll();
        // ????????? ??????
        http.exceptionHandling().accessDeniedPage("/api/user/login");

        http.logout()//.permitAll() // ???????????? ?????? ?????????
                .logoutUrl("Logout") // ???????????? ?????? URL, default: /logout, ??????????????? post ????????? ??????
                .logoutSuccessUrl("/api/user/login") // ???????????? ?????? ??? ???????????????
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }
//    protected void cofigure(HttpSecurity http) throws Exception {
//        // ????????????
//        http.logout()//.permitAll() // ???????????? ?????? ?????????
//                .logoutUrl("Logout") // ???????????? ?????? URL, default: /logout, ??????????????? post ????????? ??????
//                .logoutSuccessUrl("/api/user/login") // ???????????? ?????? ??? ???????????????
//                .deleteCookies("JSESSIONID", "remember-me"); // ???????????? ??? ?????? ??????
////                .addLogoutHandler(logoutHandler()) // ???????????? ????????? ????????? ?????? ??????
////                .logoutSuccessHandler(logoutSuccessHandler()); // ???????????? ?????? ??? ?????????
//    }

//    private LogoutHandler logoutHandler() {
//    }
//
//    private LogoutSuccessHandler logoutSuccessHandler() {
//        return null;
//    }

    // ??? ????????? ?????????, ????????? ??????????????? CorsFilter??? Security??? filter??? ????????????
    // ?????? ????????? ?????? ????????? ????????? ?????????.
    // CorsFilter??? ?????? ????????? ???????????????, CorsFilter??? ??????????????? ????????? ?????????!
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();

        // ????????? ????????? ????????? ??????
        config.addAllowedOrigin("http://localhost:3000");
        //config.addAllowedOrigin("http://charleybucket.s3-website.ap-northeast-2.amazonaws.com");

        // ?????? ????????? ??????????????? ????????? ????????? ??? ?????? ??????
        // ?????? ???????????? ????????????, Authorization ?????? ?????? ?????? ?????? ????????? ??? ?????????
        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);

        // ??? ????????? ????????? HTTP method(?????? ????????? ?????? ?????? ????????? ?????????)
        config.addAllowedMethod("*");

        // ??? ????????? ????????? HTTP header(?????? ????????? ?????? ?????? ????????? ?????????)
        config.addAllowedHeader("*");

        // ??????????????? ?????????????????? ?????? ?????? ???????????? ?????? ????????? ?????? ??????
        // ??? ????????? ????????? ?????????????????? ?????? ?????? ???????????? ?????? ????????? ?????? ??? ????????? ????????????.
        config.setAllowCredentials(true);

        // allowCredentials ??? true??? ????????? ???,
        // allowedOrigin??? ?????? * (???, ?????? ??????)??? ????????? ??? ????????? ???????????????.
        config.validateAllowCredentials();

        // ?????? ????????? ??? ????????? ????????? ??? ???????????????. (???????????? ?????? ??????)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

//    @Bean
//    public JwtAuthFilter jwtAuthFilter() {
//
//        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(
//                jwtAuthenticationSuccessHandler(),
//                jwtAuthenticationFailureHandler(),
//                om);
//
//        jwtAuthFilter.setAuthenticationManager(authenticationManager());
//
//        return jwtAuthFilter;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//
//        return new ProviderManager(jwtAuthenticationProvider());
//    }
//
//    @Bean
//    public JwtAuthenticationProvider jwtAuthenticationProvider() {
//        return new JwtAuthenticationProvider(
//                userDetailsService(),
//                passwordEncoder());
//    }
//
//    @Bean
//    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
//        return new JwtAuthenticationSuccessHandler(jwtProvider);
//    }
//
//    @Bean
//    public JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler() {
//        return new JwtAuthenticationFailureHandler(om);
//    }
//
//    @Bean
//    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
//        return new JwtAuthenticationEntryPoint(om);
//    }
//
//    @Bean
//    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
//        return new JwtAuthFilter(jwtUtil);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailsServiceImpl(userRepository);
//    }
}

/*
- **CSRF(????????? ??? ?????? ??????, Cross-site request forgery)**
    - ???????????? ????????? ??????????????? ????????? ????????? ?????? ????????? ???????????? ??? ????????? ???????????? ???????????? ?????? ????????? ???????????? ???
    - CSRF ????????? ???????????? ?????? html ?????? CSRF ?????? ?????? ??????????????? ????????? ?????? ??????
    - ?????? ????????? ???????????? ????????? ?????? ?????? ????????? REST ????????? API????????? disable ??????
    - POST ???????????? ????????? ?????? ?????? **CSRF protection ??? disable**
 */