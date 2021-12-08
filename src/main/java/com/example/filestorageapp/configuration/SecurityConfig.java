package com.example.filestorageapp.configuration;

import com.example.filestorageapp.filter.CustomAuthJwtFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration @EnableWebSecurity @RequiredArgsConstructor @Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthJwtFilter authFilter = new CustomAuthJwtFilter(super.authenticationManagerBean());
        //custom url for api login
        authFilter.setFilterProcessesUrl("/cloud/login");


        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //http.authorizeRequests().antMatchers("/cloud/login/**", "cloud/file/**","cloud/auth_token/refresh/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        //http.authorizeRequests().anyRequest().authenticated();

        http.logout(logout -> logout
                .logoutUrl("/cloud/logout")
                .addLogoutHandler((request, response, auth) -> {
                    try {
                        request.logout();
                        log.info("logout ok.");
                    } catch (ServletException e) {
                        log.error(e.getMessage());
                    }

                    response.setStatus(HttpStatus.OK.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    Map<String, String> responseBag = new HashMap<>();
                    responseBag.put("message", "ok");
                    try {
                        new ObjectMapper().writeValue(response.getOutputStream(), responseBag);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        //http.addFilter(authFilter);
        //http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
