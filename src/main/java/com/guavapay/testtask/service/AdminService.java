package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.User;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    List<Admin> getAllAdmins();

    Admin getAdminById(UUID id);

    Admin createAdmin(Admin user);

    Admin updateAdmin(UUID id, Admin user);

    void deleteAdmin(UUID id);
}