package com.guavapay.testtask.repository;

import com.guavapay.testtask.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Courier findByLogin(String login);
}