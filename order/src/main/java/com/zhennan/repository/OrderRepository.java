package com.zhennan.repository;

import com.zhennan.entity.Order;

import java.util.List;

public interface OrderRepository {


    public List<Order> findAllByState(int index, int limit, int state);
    public void updateState(long id);
    public int countByUId(long uid);
    public int countUnprocessed();
    public void save(Order order);
    public List<Order> findAllByUId(int index, int limit, long uid);
}
