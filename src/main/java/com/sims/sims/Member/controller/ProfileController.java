package com.sims.sims.Member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sims.sims.Member.services.interfaces.ProfileService;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;
import com.sims.sims.shared.dtos.ResponseDto;
import com.sims.sims.shared.dtos.UpdateProfileDto;
import com.sims.sims.shared.services.FileStorageService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<ResponseDto> getProfile() {
        ProfileResponseWithEmailDto profile = profileService.getProfile();
        ResponseDto response = new ResponseDto(0, "Success", profile);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProfile(@Valid @RequestBody UpdateProfileDto profile) {
        ProfileResponseWithEmailDto updatedProfile = profileService.updateProfile(profile);
        ResponseDto response = new ResponseDto(0, "Success", updatedProfile);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/image")
    public ResponseEntity<ResponseDto> updateProfileImage(@Valid @RequestParam("image") MultipartFile imageUrl) {
        String path = fileStorageService.storeFile(imageUrl, "profile-images/");
        ProfileResponseWithEmailDto updatedProfile = profileService.updateProfileImage(path);
        ResponseDto response = new ResponseDto(0, "Success", updatedProfile);
        return ResponseEntity.ok(response);
    }
}
