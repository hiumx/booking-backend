package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.request.IntrospectRequest;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.dto.response.IntrospectResponse;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;

    @Value("${jwt.signerKey}")
    protected static final String SIGNER_KEY
            = "WUDIpBC14uxAzco8EZo8Llkuz1l1Q05IXnYBv7mOZLrrlJVWvK/ytCDoak0OdkgL";
    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        var user = !(authenticationRequest.getEmail() == null)
                ? userRepository.findByEmail(authenticationRequest.getEmail())
                : userRepository.findByPhone(authenticationRequest.getPhone());

        if(user.isEmpty()) throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isCorrect = passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword());
        if(!isCorrect) throw new ApplicationException(ErrorCode.PASSWORD_INCORRECT);

        String token = generateToke(user.get());

        return AuthenticationResponse.builder()
                .id(user.get().getId())
                .name(user.get().getName())
                .phone(user.get().getPhone())
                .image(user.get().getImage())
                .email(user.get().getEmail())
                .dob(user.get().getDob())
                .address(user.get().getAddress())
                .role(user.get().getRole())
                .gender(user.get().getGender())
                .token(token)
                .build();
    }

    public IntrospectResponse verityToken(IntrospectRequest request)
            throws JOSEException, ParseException {
        String token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT =SignedJWT.parse(token);
        Date expire = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.builder()
                .valid(verified && expire.after(new Date()))
                .build();
    }

    private String generateToke(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        String subject = user.getEmail() != null ? user.getEmail() : user.getPhone();

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("hiumx.online")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", user.getRole().getName().toUpperCase())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
