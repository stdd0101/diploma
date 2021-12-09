package com.example.filestorageapp.configuration;

import com.example.filestorageapp.config.JwtAuthenticationEntryPoint;
import com.example.filestorageapp.filter.CustomAuthJwtFilter;
import com.example.filestorageapp.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity @RequiredArgsConstructor @Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${application.jwt.secret}")
    private String secret;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthJwtFilter authFilter = new CustomAuthJwtFilter(super.authenticationManagerBean(), secret);
        //custom url for api login
        authFilter.setFilterProcessesUrl("/cloud/login");

        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(
                "/cloud/login",
                "cloud/auth_token/refresh/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(authFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(secret), UsernamePasswordAuthenticationFilter.class);

//        http.logout(logout -> logout
//                .logoutUrl("/cloud/logout")
//                .addLogoutHandler((request, response, auth) -> {
//                    try {
//                        request.logout();
//                        log.info("logout ok.");
//                    } catch (ServletException e) {
//                        log.error(e.getMessage());
//                    }
//
//                    response.setStatus(HttpStatus.OK.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    Map<String, String> responseBag = new HashMap<>();
//                    responseBag.put("message", "Success logout");
//                    try {
//                        new ObjectMapper().writeValue(response.getOutputStream(), responseBag);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }));

    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
