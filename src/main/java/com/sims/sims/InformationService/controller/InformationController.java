package com.sims.sims.InformationService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sims.sims.shared.dtos.ResponseDto;

@RequestMapping("/informations")
@RestController
public class InformationController {
    @GetMapping("/banner")
    public ResponseEntity<ResponseDto> getBanner() {
        // Implementation here
        return ResponseEntity.ok(new ResponseDto(0, "Success", "Banner Information"));
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseDto> getServices() {
        // Implementation here
        return ResponseEntity.ok(new ResponseDto(0, "Success", "Services Information"));
    }
}
