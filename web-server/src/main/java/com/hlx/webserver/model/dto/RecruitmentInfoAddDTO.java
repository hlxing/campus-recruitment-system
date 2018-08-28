package com.hlx.webserver.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 招聘信息添加DTO
 * @author: hlx 2018-08-28
 **/
@Data
@ApiModel(description = "招聘信息")
public class RecruitmentInfoAddDTO {

    // 名称
    @ApiModelProperty(required = true, name = "信息名称", example = "Java开发实习生")
    private String name;

    // 方向
    @ApiModelProperty(required = true, name = "工作方向", example = "技术类")
    private Integer directionId;

    // 类别
    @ApiModelProperty(required = true, name = "工作类别", example = "Java")
    private Integer typeId;

    // 公司
    @ApiModelProperty(required = true, name = "公司", example = "深圳市威立聚网络科技有限公司")
    private String company;

    // 地点
    @ApiModelProperty(required = true, name = "工作地点", example = "深圳")
    private Integer addressId;

    // 职责
    @ApiModelProperty(required = true, name = "负责前期相关技术文档的编写及整理工作")
    private String responsibility;

    // 要求
    @ApiModelProperty(required = true, name = "大专及以上学历，理工科专业优先")
    private String requirement;

    public RecruitmentInfoAddDTO() {
    }
}
