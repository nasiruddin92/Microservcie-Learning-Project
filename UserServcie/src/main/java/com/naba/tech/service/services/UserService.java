package com.naba.tech.service.services;

import com.naba.tech.service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();

    User getUserById(String userId);

    User updateUser(User user);

    void DeleteUserById(User user);
}
