package com.hlx.webserver.model.po;

import lombok.Data;
import java.io.Serializable;
import java.util.Set;

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

    // 角色集合
    private Set<String> roles;

    // 权限集合
    private Set<String> permissions;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {}

}
