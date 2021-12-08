//package com.example.filestorageapp.config;
//
//import com.example.filestorageapp.service.JwtUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    private final JwtUserDetailsService jwtUserDetailsService;
//
//    private final JwtRequestFilter jwtRequestFilter;
//
//
//    public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//                             JwtUserDetailsService jwtUserDetailsService,
//                             JwtRequestFilter jwtRequestFilter) {
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtUserDetailsService = jwtUserDetailsService;
//        this.jwtRequestFilter = jwtRequestFilter;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth
//                .parentAuthenticationManager(authenticationManagerBean())
//                .userDetailsService(jwtUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // We don't need CSRF for this app
//                .csrf().disable()
//                // dont authenticate this particular request
//                .authorizeRequests()
//                .antMatchers("/cloud/login", "/test")
//                .permitAll()
//                // all other requests need to be authenticated
//                .anyRequest().authenticated().and()
//                // make sure we use stateless session; session won't be used to
//                // store user's state.
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }
//}
//
