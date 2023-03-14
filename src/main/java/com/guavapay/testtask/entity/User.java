package com.guavapay.testtask.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
//@AllArgsConstructor
public class User extends BaseUser{

}
