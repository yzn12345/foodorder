package com.zhennan.controller;

import com.zhennan.entity.Admin;
import com.zhennan.entity.User;
import com.zhennan.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {


    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
    @PostMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap)object;
        String result = null;
        if (object == null) {
            result = "login";
        }
        else {
            switch(type) {
                case "user":
                    User user = new User();
                    user.setId((int)hashMap.get("id"));
                    user.setNickname((String) hashMap.get("nickname"));

                    session.setAttribute("user", user);
                    //转发形式，而不是重定向，这样可以拿到session
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    admin.setId((int) hashMap.get("id"));
                    admin.setUsername((String) hashMap.get("username"));
                    session.setAttribute("admin", admin);
                    result = "main";
                    break;
            }
        }
        return result;
    }
}
