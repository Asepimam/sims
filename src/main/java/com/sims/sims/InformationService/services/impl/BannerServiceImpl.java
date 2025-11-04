package com.sims.sims.InformationService.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sims.sims.InformationService.entities.Banner;
import com.sims.sims.InformationService.repositories.impl.BannerRepositoryImpl;
import com.sims.sims.InformationService.services.interfaces.BannerService;
import com.sims.sims.shared.dtos.BannerResponseDto;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepositoryImpl bannerRepository;

    @Override
    public List<BannerResponseDto> getAllBanners() {
        List<Banner> banners = bannerRepository.getAllBanners();
        return banners.stream().map(banner -> {
            BannerResponseDto dto = new BannerResponseDto();
            dto.setBannerName(banner.getBannerName());
            dto.setBannerImage(banner.getBannerImage());
            dto.setDescription(banner.getDescription());
            return dto;
        }).toList();
    }
    
}