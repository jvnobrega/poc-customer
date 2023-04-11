package com.nobrega.poc.customer.infra.security;

import com.nobrega.poc.customer.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var token = getToken(request);
        if (nonNull(token)) {
            var subject = tokenService.getSubject(token);
            var user = userRepository.findByUsername(subject);
            SecurityContextHolder.getContext()
                    .setAuthentication(getAuthentication(user));
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        return ofNullable(request.getHeader("Authorization"))
                .map(authorizationHeader -> authorizationHeader.replace(BEARER, ""))
                .orElse(null);
    }

    private static UsernamePasswordAuthenticationToken getAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
    }
}
