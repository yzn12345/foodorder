package com.zhennan.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//对menu实体类进行封装， 因为layui要求的格式包含其他格式而不只是data
public class MenuVO {
    private int code;
    private String msg;
    private int count;
    private List<Menu> data;
}
