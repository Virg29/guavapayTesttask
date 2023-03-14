package com.guavapay.testtask.service.impl;

import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.repository.CourierRepository;
import com.guavapay.testtask.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void deleteCourier(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Courier> getAllCouriers() {
        return repository.findAll();
    }

    @Override
    public Courier getCourierById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Courier createCourier(Courier courier) {
        return repository.save(courier);
    }

    @Override
    public Courier updateCourier(UUID id, Courier courier) {
        Courier existingCourier = repository.findById(id).orElse(null);
        if (existingCourier != null) {
            existingCourier.setLogin(courier.getLogin());
            existingCourier.setPassword(courier.getPassword());
            return repository.save(existingCourier);
        }
        return null;
    }

    @Override
    public BaseUser getBaseUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}