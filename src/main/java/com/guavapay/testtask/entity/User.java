package com.guavapay.testtask.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
public class User extends BaseUser{
    public User() {
    }

    public User(String login, String password) {
        super(login, password);
    }
}
