package com.hlx.webserver.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作方向模型
 * @author: hlx 2018-08-26
 **/
@Data
public class JobDirection implements Serializable {

    private Integer id;

    private String name;

    public JobDirection() {
    }

}
