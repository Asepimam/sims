package com.sims.sims.Member.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sims.sims.Member.entities.User;
import com.sims.sims.Member.repositories.interfaces.ProfileRepository;
import com.sims.sims.Member.repositories.interfaces.UserRepository;
import com.sims.sims.Member.services.interfaces.ProfileService;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;
import com.sims.sims.shared.dtos.UpdateProfileDto;

import jakarta.transaction.Transactional;



@Service 
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ProfileResponseWithEmailDto getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        ProfileResponseWithEmailDto profile = profileRepository.getProfileWithEmailById(user.getId());
        return profile;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProfileResponseWithEmailDto updateProfile(UpdateProfileDto profile) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        ProfileResponseWithEmailDto updatedProfile = profileRepository.updateProfile(user.getId(), profile);
        return updatedProfile;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProfileResponseWithEmailDto updateProfileImage(String profileImageUrl) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        ProfileResponseWithEmailDto updatedProfile = profileRepository.updateProfileImage(user.getId(), profileImageUrl);
        return updatedProfile;
    }
    
}
