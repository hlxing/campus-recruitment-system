package com.hlx.webserver.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 工作地点模型
 * @author: hlx 2018-08-26
 **/
@Data
@ApiModel(description = "工作地点")
public class JobAddress implements Serializable{

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(required = true, value = "地点名称", example = "杭州")
    private String name;

    // 首字母
    @ApiModelProperty(hidden = true)
    private String firstLetter;

    // 拼音
    @ApiModelProperty(required = true, value = "名称拼音", example = "hangzhou")
    private String phonetic;

    public JobAddress() {
    }

    public JobAddress(String name, String firstLetter, String phonetic) {
        this.name = name;
        this.firstLetter = firstLetter;
        this.phonetic = phonetic;
    }
}
