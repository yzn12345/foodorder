package com.zhennan.controller;


import com.zhennan.entity.Menu;
import com.zhennan.entity.Order;
import com.zhennan.entity.OrderVO;
import com.zhennan.entity.User;
import com.zhennan.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;
    @GetMapping("save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session){
        User user = (User)session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        order.setState(0);
        order.setDate(new Date());
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/findAllByUId")
    @ResponseBody
    public OrderVO findAllByUId(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {

        User user = (User)session.getAttribute("user");
        int index = (page-1)*limit;
        return orderFeign.findAllByUId(index,limit,user.getId());
    }

    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page-1)*limit;
        return orderFeign.findAllByState(index,limit,0);
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long id) {
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }
}
