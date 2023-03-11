package com.guavapay.testtask.service.impl;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.repository.AdminRepository;
import com.guavapay.testtask.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public void deleteUser(UUID id) {
        adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getUserById(UUID id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin createUser(Admin user) {
        return adminRepository.save(user);
    }

    @Override
    public Admin updateUser(UUID id, Admin user) {
        Admin existingUser = adminRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            return adminRepository.save(existingUser);
        }
        return null;
    }
}
