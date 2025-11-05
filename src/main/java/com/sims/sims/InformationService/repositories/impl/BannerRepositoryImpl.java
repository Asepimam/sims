package com.sims.sims.InformationService.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sims.sims.InformationService.entities.Banner;
import com.sims.sims.InformationService.repositories.interfaces.BannerRepository;
import com.sims.sims.shared.exception.ResourceNotFoundException;

@Repository
public class BannerRepositoryImpl implements BannerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Banner> getAllBanners() {
        try {
            String sql = "SELECT banner_name, banner_image, description FROM banners";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                Banner banner = new Banner();
                banner.setBannerName(rs.getString("banner_name"));
                banner.setBannerImage(rs.getString("banner_image"));
                banner.setDescription(rs.getString("description"));
                return banner;
            });
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving banners");
        }
    }

    
}