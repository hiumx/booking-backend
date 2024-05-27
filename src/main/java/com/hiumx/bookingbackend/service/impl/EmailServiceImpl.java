package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.EmailRequest;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    private UserRepository userRepository;

//    @Value("${random-password}")
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

//    @Value("${spring.mail.username}")
    private final String from = "maixuanhieu250123@gmail.com";

//    @Value("${}")
    @Override
    public void sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);

        User userFounded = userRepository.findByEmail(request.getToEmail());
        if(userFounded == null) throw new ApplicationException(ErrorCode.EMAIL_INVALID);

        String passwordRaw = generateRandomPassword();

        message.setTo(request.getToEmail());
        message.setSubject("Reset Password");
        message.setText("Your reset password: " + passwordRaw);

        try {
            mailSender.send(message);
            System.out.println("Mail Sent Successfully...");

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            userFounded.setPassword(passwordEncoder.encode(passwordRaw));

            userRepository.save(userFounded);
        }catch (RuntimeException error) {
            throw new RuntimeException(error);
        }

    }

    private String generateRandomPassword() {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }
}
