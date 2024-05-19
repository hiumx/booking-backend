package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ApiResponse<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse res = authenticationService.signIn(authenticationRequest);
        return ApiResponse.builder()
                .code(1000)
                .message("Sign in successfully")
                .metadata(res)
                .build();
    }
}
