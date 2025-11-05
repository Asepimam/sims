package com.sims.sims.InformationService.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sims.sims.InformationService.entities.Service;
import com.sims.sims.InformationService.repositories.interfaces.ServiceSimsRepository;
import com.sims.sims.shared.exception.ResourceNotFoundException;

@Repository
public class ServiceSimsRepositoryImpl implements ServiceSimsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Service> getAllServices() {
        try {
        String sql = "SELECT service_code, service_name, service_icon, service_tarif FROM services";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Service service = new Service();
            service.setServiceName(rs.getString("service_name"));
            service.setServiceIcon(rs.getString("service_icon"));
            service.setServiceCode(rs.getString("service_code"));
            service.setServiceTarif(rs.getDouble("service_tarif"));
            return service;
        });
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving services");
        }
    }
    
}
