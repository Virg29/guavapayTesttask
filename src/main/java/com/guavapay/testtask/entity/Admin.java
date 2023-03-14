package com.guavapay.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "admins")
@Data
@RequiredArgsConstructor
//@AllArgsConstructor
public class Admin extends BaseUser{

}
