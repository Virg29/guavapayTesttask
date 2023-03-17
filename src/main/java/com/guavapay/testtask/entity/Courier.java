package com.guavapay.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "couriers")
@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Courier extends BaseUser {
    @Enumerated(EnumType.STRING)
    private CourierStatus status;
    public Courier() {
    }

    public Courier(String login, String password) {
        super(login, password);
    }
}