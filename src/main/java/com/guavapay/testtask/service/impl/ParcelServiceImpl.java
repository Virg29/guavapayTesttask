package com.guavapay.testtask.service.impl;

import com.guavapay.testtask.entity.Parcel;
import com.guavapay.testtask.repository.ParcelRepository;
import com.guavapay.testtask.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository repository;
    @Override
    public void deleteParcel(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Parcel> getAllParcels() {
        return repository.findAll();
    }

    @Override
    public Parcel getParcelById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Parcel createParcel(Parcel parcel) {
        return repository.save(parcel);
    }

    @Override
    public Parcel updateParcel(UUID id, Parcel parcel) {
        Parcel existingParcel = repository.findById(id).orElse(null);
        if (existingParcel != null) {
            existingParcel.setDaddress(parcel.getDaddress());
            existingParcel.setUser(parcel.getUser());
            existingParcel.setCourier(parcel.getCourier());
            existingParcel.setLat(parcel.getLat());
            existingParcel.setLng(parcel.getLng());
            existingParcel.setStatus(parcel.getStatus());
            return repository.save(existingParcel);
        }
        return null;
    }
}