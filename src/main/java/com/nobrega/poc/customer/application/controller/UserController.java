package com.nobrega.poc.customer.application.controller;

import com.nobrega.poc.customer.application.request.UserAuthenticationRequest;
import com.nobrega.poc.customer.application.response.TokenResponse;
import com.nobrega.poc.customer.domain.user.User;
import com.nobrega.poc.customer.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid UserAuthenticationRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }

}
