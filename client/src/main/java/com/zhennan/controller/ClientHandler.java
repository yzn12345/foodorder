package com.zhennan.controller;

import com.zhennan.entity.Menu;
import com.zhennan.entity.MenuVO;
import com.zhennan.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//返回 数据
//@RestController
//返回 视图
@Controller
@RequestMapping("/client")
public class ClientHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page-1)*limit;
        return menuFeign.findAll(index, limit);

    }

    //access layui index.html
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        menuFeign.deleteById(id);
        return "redirect:/client/redirect/index";
    }

}
