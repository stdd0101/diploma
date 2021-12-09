package com.example.filestorageapp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.filestorageapp.model.ErrorBag;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private String secret;

    public CustomAuthorizationFilter(String secret) {
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("cloud/login")
                || request.getServletPath().equals("api/auth_token/refresh")
                || request.getServletPath().equals("api/logout")) {
            filterChain.doFilter(request, response);
        } else {
            log.info(request.getHeader(HttpHeaders.AUTHORIZATION));
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                try {
                    String token = authHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);

                } catch (Exception e) {
                    Integer status = HttpStatus.FORBIDDEN.value();

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(status);

                    ErrorBag error = new ErrorBag()
                            .setMessage("Unsuccessful authentication.")
                            .setStatus(status);

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
