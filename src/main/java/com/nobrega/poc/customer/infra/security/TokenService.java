package com.nobrega.poc.customer.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nobrega.poc.customer.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

import static java.time.LocalDateTime.now;

@Service
public class TokenService {

    private static final ZoneOffset SAO_PAULO_TIME_ZONE = ZoneOffset.of("-03:00");
    private static final String POC_CUSTOMER_ISSUER = "poc-customer";
    @Value("${api.security.token.secret}")
    private String secret;

    public String getToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(POC_CUSTOMER_ISSUER)
                    .withSubject(user.getUsername())
                    .withClaim("uuid", user.getUuid())
                    .withExpiresAt(getExpiresAt())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new IllegalArgumentException("Error generating token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(secret))
                    .withIssuer(POC_CUSTOMER_ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new IllegalArgumentException("Error getting Subject from token jwt", exception);
        }
    }

    private static Instant getExpiresAt() {
        return now()
                .plusHours(2)
                .toInstant(SAO_PAULO_TIME_ZONE);
    }
}
