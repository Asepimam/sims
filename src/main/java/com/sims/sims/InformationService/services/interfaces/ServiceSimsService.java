package com.sims.sims.InformationService.services.interfaces;

import java.util.List;

import com.sims.sims.shared.dtos.ServiceSimsResponseDto;

public interface ServiceSimsService {
    List<ServiceSimsResponseDto> getAllServices();
}
