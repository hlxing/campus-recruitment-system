package com.hlx.webserver.model;

import lombok.Data;
import java.io.Serializable;

/**
 * @description: 用户实体
 * @author: hlx 2018-08-14
 **/
@Data
public class User implements Serializable {

    private String name;

    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {}

}
