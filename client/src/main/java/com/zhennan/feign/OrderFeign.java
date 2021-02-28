package com.zhennan.feign;

import com.zhennan.entity.MenuVO;
import com.zhennan.entity.Order;
import com.zhennan.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/order/update/{id}")
    public void updateState(@PathVariable("id") long id);
    @GetMapping("/order/findAllByState/{index}/{limit}/{state}")
    public OrderVO findAllByState(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("state") int state);
    @PostMapping("/order/save")
    public void save(Order order);
    @GetMapping("/order/findAllByUId/{index}/{limit}/{uid}")
    public OrderVO findAllByUId(@PathVariable("index") int index,@PathVariable("limit") int limit,@PathVariable("uid") long uid);
}

