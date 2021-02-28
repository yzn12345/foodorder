package com.zhennan.repository;

import com.zhennan.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);

}
