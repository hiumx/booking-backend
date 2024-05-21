package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;

import java.util.List;

public interface UserService {
    UserCreationResponse createUser(UserCreationRequest userCreationRequest);
    UserCreationResponse getUserById(Long id);
    List<UserCreationResponse> getAllUsers();
    UserCreationResponse updateUser(Long id, UserCreationRequest userCreationRequest);

    UserCreationResponse getMyInfo();

}
