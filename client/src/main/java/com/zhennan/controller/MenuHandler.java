package com.zhennan.controller;

import com.zhennan.entity.Menu;
import com.zhennan.entity.MenuVO;
import com.zhennan.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//返回 数据
//@RestController
//返回 视图
@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        //分页
        int index = (page-1)*limit;
        return menuFeign.findAll(index, limit);

    }

    //access layui menu_manage.html
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/addMenu")
    public ModelAndView addMenu() {
        ModelAndView modelandview = new ModelAndView();
        modelandview.setViewName("menu_add");
        modelandview.addObject("list", menuFeign.findTypes());
        return modelandview;
    }

    @PostMapping("/save")
    public String save(Menu menu) {
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @PostMapping("/update")
    public String update(Menu menu) {
        menuFeign.update(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long  id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu", menuFeign.findById(id));
        //得知道分类才能更新
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }
}
