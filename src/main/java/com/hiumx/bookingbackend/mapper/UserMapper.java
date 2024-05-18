package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.UserDto;
import com.hiumx.bookingbackend.entity.User;

public class UserMapper {
    static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getDob(),
                userDto.getGender(),
                userDto.getAddress(),
                userDto.getImage(),
                userDto.getRole(),
                userDto.getIsActive()
        );
    }

    static UserDto mapToUser(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPhone(),
                user.getName(),
                user.getPassword(),
                user.getDob(),
                user.getGender(),
                user.getAddress(),
                user.getImage(),
                user.getRole(),
                user.getIsActive()
        );
    }
}
