package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.UserDto;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }
}
