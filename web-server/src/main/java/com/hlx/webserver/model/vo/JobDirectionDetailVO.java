package com.hlx.webserver.model.vo;

import com.hlx.webserver.model.po.JobType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 工作方向详细信息VO
 * @author: hlx 2018-08-26
 **/
@ApiModel(description = "工作方向详细信息")
@Data
public class JobDirectionDetailVO {

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "工作类型集合")
    private List<JobType> jobTypes;

    public JobDirectionDetailVO() {
    }

}
