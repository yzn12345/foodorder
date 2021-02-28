package com.zhennan.repository;

import com.zhennan.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll(long index, long limit);
    public int count();
    public User findById(long id);
    public void save(User user);
    public void update(User user);
    public void deleteById(long id);


}
