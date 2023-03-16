package com.guavapay.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "couriers")
@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Courier extends BaseUser {
    public Courier() {
    }

    public Courier(String login, String password) {
        super(login, password);
    }
}