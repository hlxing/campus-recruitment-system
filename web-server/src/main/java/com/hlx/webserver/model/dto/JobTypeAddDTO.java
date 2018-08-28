package com.hlx.webserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 工作类型传输对象
 * @author: hlx 2018-08-26
 **/
@ApiModel(description = "工作类型")
@Data
public class JobTypeAddDTO {

    @ApiModelProperty(required = true, value = "方向id", example = "1")
    private Integer directionId;

    @ApiModelProperty(required = true, value = "类型名称", example = "Java")
    private String name;

    public JobTypeAddDTO() {
    }

}
