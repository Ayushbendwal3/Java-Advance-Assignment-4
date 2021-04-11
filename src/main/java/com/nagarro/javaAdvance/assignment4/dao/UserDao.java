package com.nagarro.javaAdvance.assignment4.dao;

import com.nagarro.javaAdvance.assignment4.model.User;

public interface UserDao {
    public void saveUser(User user);
    public User getUser(String userId);
}
