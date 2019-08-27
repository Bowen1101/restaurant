package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.User;

public interface UserDao {
    boolean save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}
