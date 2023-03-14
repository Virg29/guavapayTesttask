package com.guavapay.testtask.service.impl;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.repository.AdminRepository;
import com.guavapay.testtask.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void deleteAdmin(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }

    @Override
    public Admin getAdminById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin updateAdmin(UUID id, Admin admin) {
        Admin existingAdmin = repository.findById(id).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setLogin(admin.getLogin());
            existingAdmin.setPassword(admin.getPassword());
            return repository.save(existingAdmin);
        }
        return null;
    }

    @Override
    public BaseUser getBaseUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}
