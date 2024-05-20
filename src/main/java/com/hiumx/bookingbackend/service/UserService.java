package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;

public interface UserService {
    UserCreationResponse createUser(UserCreationRequest userCreationRequest);

    UserCreationResponse getUserById(Long id);
}
