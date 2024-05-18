package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genders")
@AllArgsConstructor
public class GenderController {

    private GenderService genderService;

    @PostMapping
    ResponseEntity<GenderDto> createGender(@RequestBody GenderDto genderDto) {
        GenderDto genderSaved = genderService.createGender(genderDto);
        return ResponseEntity.ok(genderSaved);
    }
}
