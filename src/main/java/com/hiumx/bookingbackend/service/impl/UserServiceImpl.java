package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.mapper.UserMapper;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserCreationResponse createUser(UserCreationRequest request) {

        if(request.getEmail() == null && request.getPhone() == null)
            throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);

        if(request.getGenderId() == null)
            request.setGenderId(1L);

        if(request.getIsActive() == null)
            request.setIsActive(1);

        if(request.getImage() == null)
            request.setImage("https://hiumx.online/image/user/defaut-img.png");

        if(request.getRoleId() == null) request.setRoleId(3L);
        User user = UserMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userSaved = userRepository.save(user);
        return UserMapper.toUserResponse(userSaved);
    }

    @PostAuthorize("returnObject.email == authentication.name || returnObject.phone == authentication.name")
    @Override
    public UserCreationResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        return UserMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserCreationResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserResponse).toList();
    }
}
