package com.guavapay.testtask.repository;

import com.guavapay.testtask.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParcelRepository extends JpaRepository<Parcel, UUID> {
}
