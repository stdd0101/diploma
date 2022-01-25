package com.example.filestorageapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.filestorageapp.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service @RequiredArgsConstructor @Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.issuer}")
    private String issuer;

    private final UserService userService;

    public AuthService setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public User getAuthUser(String username) {
        User user = userService.getUser(username);
        return user;
    }

    public String getUsernameFromToken(String token) {
        log.info("secret- {}", secret);

        Algorithm algorithm = getAlgorithm(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }

    public String refreshToken(String token) {
        String username = getUsernameFromToken(token);
        User user = getAuthUser(username);

        return getRefreshToken(user);
    }

    public String getToken(User user) {
        String auth_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(issuer)
                .sign(getAlgorithm(secret));

        log.info("auth_token {}", auth_token);
        return auth_token;
    }

    public String getRefreshToken(User user) {
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(issuer)
                .sign(getAlgorithm(secret));

        log.info("refresh_token {}", refresh_token);
        return refresh_token;
    }

    protected Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC256(secret.getBytes());
    }
}
