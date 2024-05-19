package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.AuthenticationRequest;
import com.hiumx.bookingbackend.dto.response.AuthenticationResponse;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.exception.ErrorCode;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;
    @Override
    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        var user = !authenticationRequest.getEmail().isEmpty()
                ? userRepository.findByEmail(authenticationRequest.getEmail())
                : userRepository.findByPhone(authenticationRequest.getPhone());
        System.out.println(user);
        if(user.isEmpty()) throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isCorrect = passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword());
        if(!isCorrect) throw new ApplicationException(ErrorCode.PASSWORD_INCORRECT);

        return AuthenticationResponse.builder()
                .id(user.get().getId())
                .name(user.get().getName())
                .phone(user.get().getPhone())
                .image(user.get().getImage())
                .email(user.get().getEmail())
                .dob(user.get().getDob())
                .address(user.get().getAddress())
                .gender(user.get().getGender())
                .build();
    }
}
