package com.sims.sims.InformationService.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sims.sims.InformationService.repositories.interfaces.ServiceSimsRepository;
import com.sims.sims.InformationService.services.interfaces.ServiceSimsService;
import com.sims.sims.shared.dtos.ServiceSimsResponseDto;
import com.sims.sims.shared.exception.BusinessException;

@Service
public class ServiceSimsImpl implements ServiceSimsService {
    @Autowired
    private ServiceSimsRepository serviceSimsRepository;

    @Override
    public List<ServiceSimsResponseDto> getAllServices() {
        try {
            List<com.sims.sims.InformationService.entities.Service> services = serviceSimsRepository.getAllServices();
            return services.stream().map(service -> {
                ServiceSimsResponseDto dto = new ServiceSimsResponseDto();
                dto.setServiceName(service.getServiceName());
                dto.setServiceCode(service.getServiceCode());
                dto.setServiceIcon(service.getServiceIcon());
                dto.setServiceTarif(service.getServiceTarif());
                return dto;
            }).toList();
        } catch (Exception e) {
            throw new BusinessException("Error retrieving services");
        }
    }
}