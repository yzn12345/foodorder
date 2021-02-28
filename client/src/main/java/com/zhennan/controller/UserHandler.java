package com.zhennan.controller;


import com.zhennan.entity.User;
import com.zhennan.entity.UserVO;
import com.zhennan.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page-1)*limit;
        return userFeign.findAll(index, limit);
    }


    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id) {
        return userFeign.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return userFeign.count();
    }

    @PostMapping("/save")
    //把json转成User object
    public String save(User user) {
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }

    @PutMapping("/update")
    public String update(User user) {
        userFeign.update(user);
        return "redirect:/menu/redirect/user_manage";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        userFeign.deleteById(id);
        return "redirect:/menu/redirect/user_manage";
    }
}
