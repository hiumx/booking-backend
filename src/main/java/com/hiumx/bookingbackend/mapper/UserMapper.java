package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.dto.response.UserReviewResponse;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.entity.User;

import java.util.HashSet;

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
                //.role(new Role(userCreationRequest.getRoleId()))
                .isActive(userCreationRequest.getIsActive())
                .build();

    }

    public static UserCreationResponse toUserResponse(User user) {

        return UserCreationResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .name(user.getName())
                .dob(user.getDob())
                .gender(user.getGender())
                .address(user.getAddress())
                .image(user.getImage())
                .roles((new HashSet<>(user.getRoles().stream().map(RoleMapper::toRoleResponse).toList())))
//                .hotelsSaved(new HashSet<>(user.getHotelsSaved().stream().map(HotelMapper::toHotelResponse).toList()))
                .isActive(user.getIsActive())
                .build();
    }

    public static UserReviewResponse toUserReviewResponse(User user) {
        return UserReviewResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .address(user.getAddress())
                .image(user.getImage())
                .build();
    }
}
