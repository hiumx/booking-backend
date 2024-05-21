package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.mapper.RoleMapper;
import com.hiumx.bookingbackend.mapper.UserMapper;
import com.hiumx.bookingbackend.repository.RoleRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public UserCreationResponse createUser(UserCreationRequest request) {
        System.out.println(request.getRoleIds());

        if(request.getEmail() != null) {
            User userFounded = userRepository.findByEmail(request.getEmail());
            if(userFounded != null) throw new ApplicationException(ErrorCode.EMAIL_OR_PHONE_EXISTED);
        } else {
            User userFounded = userRepository.findByPhone(request.getPhone());
            if(userFounded != null) throw new ApplicationException(ErrorCode.EMAIL_OR_PHONE_EXISTED);
        }

        if(request.getEmail() == null && request.getPhone() == null)
            throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR);

        if(request.getGenderId() == null)
            request.setGenderId(1L);

        if(request.getIsActive() == null)
            request.setIsActive(1);

        if(request.getImage() == null)
            request.setImage("https://hiumx.online/image/user/defaut-img.png");

        User user = UserMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(request.getRoleIds() == null) {
            List<Long> rolesId = new ArrayList<>();
            rolesId.add(3L);
            var roles = roleRepository.findAllById(rolesId);
            user.setRoles(new HashSet<>(roles));
        }
//        System.out.println(user);
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

    @Override
    public UserCreationResponse updateUser(Long id, UserCreationRequest request) {
        User userFounded = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        userFounded.setRoles(new HashSet<>(roles));
        return UserMapper.toUserResponse(userRepository.save(userFounded));
    }

    @Override
    public UserCreationResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String identity = context.getAuthentication().getName();

        User user = userRepository.findByEmail(identity);
        if(user == null)
            user = userRepository.findByPhone(identity);

        if(user == null)
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND);

        return UserMapper.toUserResponse(user);
    }
}
