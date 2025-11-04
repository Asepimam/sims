package com.sims.sims.Member.services.interfaces;

import com.sims.sims.shared.dtos.ProfileResponseDto;
import com.sims.sims.shared.dtos.ProfileResponseWithEmailDto;
import com.sims.sims.shared.dtos.UpdateProfileDto;

public interface ProfileService {
    ProfileResponseWithEmailDto  getProfile();
    ProfileResponseWithEmailDto updateProfile(UpdateProfileDto profile);
    ProfileResponseWithEmailDto updateProfileImage(String profileImageUrl);
}
