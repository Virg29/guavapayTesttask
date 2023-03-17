package com.guavapay.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

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
