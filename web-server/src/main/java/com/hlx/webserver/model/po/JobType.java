package com.hlx.webserver.model.po;

import lombok.Data;
import java.io.Serializable;

/**
 * @description: 工作类别模型
 * @author: hlx 2018-08-26
 **/
@Data
public class JobType implements Serializable {

    private Integer id;

    private String name;

    public JobType() {
    }

    public JobType(String name) {
        this.name = name;
    }
}
