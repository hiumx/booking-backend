package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.entity.User;

public class UserMapper {
    public static User toUser(UserCreationRequest userCreationRequest) {
        return User.builder()
                .email(userCreationRequest.getEmail())
                .phone(userCreationRequest.getPhone())
                .name(userCreationRequest.getName())
                .password(userCreationRequest.getPassword())
                .dob(userCreationRequest.getDob())
                .gender(new Gender(userCreationRequest.getGenderId()))
                .address(userCreationRequest.getAddress())
                .image(userCreationRequest.getImage())
                .role(new Role(userCreationRequest.getRoleId()))
                .isActive(userCreationRequest.getIsActive())
                .build();

    }

    public static UserCreationResponse toUserResponse(User user) {
        return new UserCreationResponse(
                user.getId(),
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getDob(),
                user.getGender(),
                user.getAddress(),
                user.getImage(),
                user.getRole(),
                user.getIsActive()
        );
    }
}
