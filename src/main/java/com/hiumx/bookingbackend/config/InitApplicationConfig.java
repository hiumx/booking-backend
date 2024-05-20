package com.hiumx.bookingbackend.config;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@Slf4j
public class InitApplicationConfig {
    private UserService userService;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                UserCreationRequest request = UserCreationRequest.builder()
                        .email("admin@gmail.com")
                        .password("admin")
                        .roleId(1L)
                        .build();
                userService.createUser(request);
                log.warn("Admin user has been created with default password: admin");
            }
        };
    }

}
