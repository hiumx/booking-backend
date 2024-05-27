package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.request.IntrospectRequest;
import com.hiumx.bookingbackend.dto.request.LogoutRequest;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse signIn(AuthenticationRequest authenticationRequest);
    IntrospectResponse verityToken(IntrospectRequest request) throws JOSEException, ParseException;

    void logout(LogoutRequest request) throws JOSEException, ParseException;
}
