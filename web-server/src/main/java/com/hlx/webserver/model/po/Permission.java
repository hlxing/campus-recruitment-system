package com.hlx.webserver.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 权限实体
 * @author: hlx 2018-08-21
 **/
@Data
public class Permission implements Serializable{

    private Integer id;

    // 权限值
    private String name;

    // 权限描述
    private String description;

    public Permission() {
    }
}
