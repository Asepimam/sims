package com.sims.sims.InformationService.services.interfaces;

import java.util.List;

import com.sims.sims.shared.dtos.BannerResponseDto;

public interface BannerService {
    List<BannerResponseDto> getAllBanners();
}
