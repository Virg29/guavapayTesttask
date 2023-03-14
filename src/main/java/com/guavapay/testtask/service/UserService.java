package com.guavapay.testtask.service;


import com.guavapay.testtask.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService extends BaseService{

    List<User> getAllUsers();

    User getUserById(UUID id);

    User createUser(User user);

    User updateUser(UUID id, User user);

    void deleteUser(UUID id);

}