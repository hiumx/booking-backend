package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.request.UserResetPasswordRequest;
import com.hiumx.bookingbackend.dto.request.UserSaveRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.dto.response.UserSaveGetResponse;
import com.hiumx.bookingbackend.dto.response.UserSaveResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserCreationResponse createUser(UserCreationRequest userCreationRequest);
    UserCreationResponse getUserById(Long id);
    List<UserCreationResponse> getAllUsers();
    UserCreationResponse updateUser(Long id, UserCreationRequest userCreationRequest);
    UserCreationResponse getMyInfo();
    UserCreationResponse updateUserByField(Long id, Map<String, Object> update);
    void resetPassword(Long id, UserResetPasswordRequest request);
    UserSaveResponse saveHotel(UserSaveRequest request);
    UserSaveGetResponse getHotelSaveByUser(Long userId);

}
