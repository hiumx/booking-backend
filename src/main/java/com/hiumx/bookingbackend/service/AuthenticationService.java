package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signIn(AuthenticationRequest authenticationRequest);
}
