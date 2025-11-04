package com.sims.sims.Member.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sims.sims.Member.entities.Profile;
import com.sims.sims.Member.entities.User;
import com.sims.sims.Member.repositories.interfaces.ProfileRepository;
import com.sims.sims.Member.repositories.interfaces.UserRepository;
import com.sims.sims.Member.services.interfaces.ProfileService;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;
import com.sims.sims.shared.dtos.UpdateProfileDto;

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
    public ProfileResponseWithEmailDto updateProfile(UpdateProfileDto profile) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Profile profileToUpdate = new Profile(
            null,
            profile.getFirstName(),
            profile.getLastName(),
            null,
            user.getId(),
            null,
            null
        );
        ProfileResponseWithEmailDto updatedProfile = profileRepository.updateProfile(user.getId(), profileToUpdate);
        return updatedProfile;
    }

    @Override
    public ProfileResponseWithEmailDto updateProfileImage(String profileImageUrl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfileImage'");
    }
    
}
