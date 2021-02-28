package com.zhennan.repository;

import com.zhennan.entity.User;

public interface UserRepository {
    public User login(String username, String password);
}
