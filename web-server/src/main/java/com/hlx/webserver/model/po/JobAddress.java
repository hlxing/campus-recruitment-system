package com.hlx.webserver.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作地点模型
 * @author: hlx 2018-08-26
 **/
@Data
public class JobAddress implements Serializable{

    private Integer id;

    private String name;

    public JobAddress() {
    }

}
