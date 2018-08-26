package com.hlx.webserver.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作方向传输对象
 * @author: hlx 2018-08-26
 **/
@ApiModel(description = "工作方向")
@Data
public class JobDirectionDTO implements Serializable {

    @ApiModelProperty(required = true, value = "方向名称", example = "技术类")
    private String name;

    public JobDirectionDTO() {
    }
}
