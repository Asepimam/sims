package com.sims.sims.InformationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sims.sims.InformationService.services.interfaces.BannerService;
import com.sims.sims.InformationService.services.interfaces.ServiceSimsService;
import com.sims.sims.shared.dtos.ResponseDto;

@RequestMapping("/informations")
@RestController
public class InformationController {
    @Autowired
    private BannerService bannerService;

    @Autowired
    private ServiceSimsService serviceSimsService;

    @GetMapping("/banner")
    public ResponseEntity<ResponseDto> getBanner() {
        return ResponseEntity.ok(new ResponseDto(0, "Success", bannerService.getAllBanners()));
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseDto> getServices() {
        return ResponseEntity.ok(new ResponseDto(0, "Success", serviceSimsService.getAllServices()));
    }
}
