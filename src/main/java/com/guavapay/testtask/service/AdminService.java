package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.User;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    List<Admin> getAllUsers();

    Admin getUserById(UUID id);

    Admin createUser(Admin user);

    Admin updateUser(UUID id, Admin user);

    void deleteUser(UUID id);
}