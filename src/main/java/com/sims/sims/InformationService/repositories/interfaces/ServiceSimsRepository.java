package com.sims.sims.InformationService.repositories.interfaces;

import java.util.List;

import com.sims.sims.InformationService.entities.Service;

public interface ServiceSimsRepository {
    List<Service> getAllServices();
}
