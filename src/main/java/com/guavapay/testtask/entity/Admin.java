package com.guavapay.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "admins")
@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Admin extends BaseUser{

    public Admin(String login, String password) {
        super(login, password);
    }

    public Admin() {
    }
}
