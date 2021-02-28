package com.zhennan.controller;

import com.zhennan.entity.Order;
import com.zhennan.entity.OrderVO;
import com.zhennan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order) {

        orderRepository.save(order);

    }

    @GetMapping("/findAllByUId/{index}/{limit}/{uid}")
    public OrderVO findAllByUId(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") long uid)  {
        OrderVO vo = new OrderVO();
        vo.setMsg("");
        vo.setData(orderRepository.findAllByUId(index,limit, uid));
        vo.setCount(orderRepository.countByUId(uid));
        return vo;
    }

    @GetMapping("/findAllByState/{index}/{limit}/{state}")
    public OrderVO findAllByState(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("state") int state) {
        OrderVO vo = new OrderVO();
        vo.setMsg("");
        vo.setCount(orderRepository.countUnprocessed());
        vo.setData(orderRepository.findAllByState(index,limit,state));
        return vo;
    }

    @GetMapping("/update/{id}")
    public void updateState(@PathVariable("id") long id) {
        orderRepository.updateState(id);
    }
    @GetMapping("/countByUId/{uid}")
    public int countByUId(@PathVariable("uid") int uid) {
        return orderRepository.countByUId(uid);
    }
}