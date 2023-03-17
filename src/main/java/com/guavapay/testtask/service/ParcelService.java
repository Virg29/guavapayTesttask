package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.Parcel;
import com.guavapay.testtask.entity.User;

import java.util.List;
import java.util.UUID;

public interface ParcelService {

    List<Parcel> getAllParcels();

    List<Parcel> getAllParcelsByUser(User user);
    List<Parcel> getAllParcelsByCourier(Courier user);

    Parcel getParcelById(UUID id);

    Parcel createParcel(Parcel parcel);

    Parcel updateParcel(UUID id, Parcel parcel);

    void deleteParcel(UUID id);
}
