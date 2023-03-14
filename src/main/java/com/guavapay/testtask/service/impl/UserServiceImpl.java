package com.guavapay.testtask.service.impl;


import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.repository.UserRepository;
import com.guavapay.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        User existingUser = repository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
        }
        return null;
    }

    @Override
    public BaseUser getBaseUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}
