package com.zhennan.entity;

import lombok.Data;

//实体类
@Data
public class Menu {
    private long id;
    private String name;
    private double price;
    private String flavor;
    private Type type;

}
