package com.hlx.webserver.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 角色实体
 * @author: hlx 2018-08-21
 **/
@Data
public class Role implements Serializable{

    private Integer id;

    // 角色值
    private String name;

    // 角色描述
    private String description;

    public Role() {
    }

}
