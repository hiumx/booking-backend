package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.request.UserResetPasswordRequest;
import com.hiumx.bookingbackend.dto.request.UserSaveRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.dto.response.UserSaveGetResponse;
import com.hiumx.bookingbackend.dto.response.UserSaveResponse;
import com.hiumx.bookingbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    ApiResponse<?> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserCreationResponse user = userService.createUser(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new user successfully")
                .metadata(user)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<?> getUserById(@PathVariable("id") Long id) {
        UserCreationResponse user = userService.getUserById(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get user successfully")
                .metadata(user)
                .build();
    }

    @GetMapping()
    ApiResponse<?> getAllUsers() {
        List<UserCreationResponse> users = userService.getAllUsers();
        return ApiResponse.builder()
                .code(1000)
                .message("Get user successfully")
                .metadata(users)
                .build();
    }

    @PutMapping("{id}")
    ApiResponse<?> updateUser(@PathVariable("id") Long id, @RequestBody UserCreationRequest request) {
        UserCreationResponse user = userService.updateUser(id, request);
        return ApiResponse.builder()
                .code(1000)
                .message("Get user successfully")
                .metadata(user)
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<?> getMyInfo() {
        UserCreationResponse user = userService.getMyInfo();
        return ApiResponse.builder()
                .code(1000)
                .message("Get my information successfully")
                .metadata(user)
                .build();
    }

    @PatchMapping("{id}")
    ApiResponse<?> updateInfoByField(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        UserCreationResponse user = userService.updateUserByField(id, updates);
        return ApiResponse.builder()
                .code(1000)
                .message("Update my information successfully")
                .metadata(user)
                .build();
    }

    @PatchMapping("/reset-password/{id}")
    ApiResponse<?> resetPassword(@PathVariable("id") Long id, @Valid @RequestBody UserResetPasswordRequest request) {
        userService.resetPassword(id, request);
        return ApiResponse.builder()
                .code(1000)
                .message("Reset password successfully")
                .build();
    }

    @PostMapping("/save-hotel")
    ApiResponse<?> saveHotel(@RequestBody UserSaveRequest request) {
        UserSaveResponse userSaveResponse = userService.saveHotel(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Save hotel successfully")
                .metadata(userSaveResponse)
                .build();
    }

    @GetMapping("/save-hotel/{id}")
    ApiResponse<?> getHotelSaveByUser(@PathVariable("id") Long userId) {
        UserSaveGetResponse userSaveResponse = userService.getHotelSaveByUser(userId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get hotels saved by user successfully")
                .metadata(userSaveResponse)
                .build();
    }

}
