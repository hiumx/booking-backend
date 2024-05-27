package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.request.IntrospectRequest;
import com.hiumx.bookingbackend.dto.request.LogoutRequest;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.dto.response.IntrospectResponse;
import com.hiumx.bookingbackend.entity.InvalidToken;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.repository.InvalidTokenRepository;
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
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private InvalidTokenRepository invalidTokenRepository;

    @Value("${jwt.signerKey}")
    protected static final String SIGNER_KEY
            = "WUDIpBC14uxAzco8EZo8Llkuz1l1Q05IXnYBv7mOZLrrlJVWvK/ytCDoak0OdkgL";
    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        var user = !(authenticationRequest.getEmail() == null)
                ? userRepository.findByEmail(authenticationRequest.getEmail())
                : userRepository.findByPhone(authenticationRequest.getPhone());

        if(user == null) throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isCorrect = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if(!isCorrect) throw new ApplicationException(ErrorCode.PASSWORD_INCORRECT);

        String token = generateToke(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .image(user.getImage())
                .email(user.getEmail())
                .dob(user.getDob())
                .address(user.getAddress())
                .role(user.getRoles())
                .gender(user.getGender())
                .token(token)
                .build();
    }

    public IntrospectResponse verityToken(IntrospectRequest request)
            throws JOSEException, ParseException {
        String token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (ApplicationException exception) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(request.getToken());
        String tokenId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expireTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        invalidTokenRepository.save(new InvalidToken(tokenId, expireTime));
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT =SignedJWT.parse(token);
        String tokenId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expire = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(jwsVerifier);
        if(!(verified && expire.after(new Date())))
            throw new ApplicationException(ErrorCode.UNAUTHENTICATED);

        if(invalidTokenRepository.existsById(tokenId))
            throw new ApplicationException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
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
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
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

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName().toUpperCase());
                if(!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });
        }
        return stringJoiner.toString();
    }
}
