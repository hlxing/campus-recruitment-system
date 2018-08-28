package com.hlx.webserver.model.po;

import lombok.Data;
import java.io.Serializable;

/**
 * @description: 用户实体
 * @author: hlx 2018-08-14
 **/
@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private String email;

    private Long createTime;

    private String password;

    private String sessionId;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {}

}
