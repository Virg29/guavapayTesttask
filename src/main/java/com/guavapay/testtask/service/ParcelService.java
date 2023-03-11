package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.Parcel;

import java.util.List;
import java.util.UUID;

public interface ParcelService {

    List<Parcel> getAllParcels();

    Parcel getParcelById(UUID id);

    Parcel createParcel(Parcel user);

    Parcel updateParcel(UUID id, Parcel user);

    void deleteParcel(UUID id);
}
