package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.request.IntrospectRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.dto.response.IntrospectResponse;
import com.hiumx.bookingbackend.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ApiResponse<?> signIn(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse res = authenticationService.signIn(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Sign in successfully")
                .metadata(res)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<?> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectResponse response = authenticationService.verityToken(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Verify token successfully")
                .metadata(response)
                .build();
    }
}
