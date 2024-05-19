package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.UserCreationRequest;
import com.hiumx.bookingbackend.dto.response.UserCreationResponse;
import com.hiumx.bookingbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    ResponseEntity<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        UserCreationResponse user = userService.createUser(userCreationRequest);
        return ResponseEntity.ok(user);
    }

}
