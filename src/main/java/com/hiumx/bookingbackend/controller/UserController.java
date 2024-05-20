package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    ResponseEntity<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserCreationResponse user = userService.createUser(request);
        return ResponseEntity.ok(user);
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

}
