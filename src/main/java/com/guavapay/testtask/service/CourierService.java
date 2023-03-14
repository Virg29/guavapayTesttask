package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.Courier;

import java.util.List;
import java.util.UUID;

public interface CourierService extends BaseService {

    List<Courier> getAllCouriers();

    Courier getCourierById(UUID id);


    Courier createCourier(Courier user);

    Courier updateCourier(UUID id, Courier user);

    void deleteCourier(UUID id);
}