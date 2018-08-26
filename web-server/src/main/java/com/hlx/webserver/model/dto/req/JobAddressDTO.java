package com.hlx.webserver.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作地点DTO
 * @author: hlx 2018-08-26
 **/
@ApiModel(description = "工作地点")
@Data
public class JobAddressDTO implements Serializable{

    @ApiModelProperty(required = true, value = "地点名称", example = "杭州")
    private String name;

    public JobAddressDTO() {
    }

}
