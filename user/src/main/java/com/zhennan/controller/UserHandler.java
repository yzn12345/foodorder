package com.zhennan.controller;


import com.zhennan.entity.User;
import com.zhennan.entity.UserVO;
import com.zhennan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Value("${server.port}")
    private String port;


    @GetMapping("/index")
    public String index() {
        return this.port;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") long index, @PathVariable("limit") long limit) {
        List<User> list = userRepository.findAll(index, limit);

        //count is the number of menu
        return new UserVO(0,"", userRepository.count(), list);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return userRepository.count();
    }

    @PostMapping("/save")
    //把json转成User object
    //feign靠json传递，所以需要requestbody
    public void save(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        userRepository.update(user);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        userRepository.deleteById(id);
    }


}
