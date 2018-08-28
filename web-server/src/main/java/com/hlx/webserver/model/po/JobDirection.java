package com.hlx.webserver.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作方向模型
 * @author: hlx 2018-08-26
 **/
@Data
@ApiModel(description = "工作方向")
public class JobDirection implements Serializable {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(required = true, name = "方向名称", example = "技术类")
    private String name;

    public JobDirection() {
    }

}
