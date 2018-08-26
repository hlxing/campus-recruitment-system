package com.hlx.webserver.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 招聘信息模型
 * @author: hlx 2018-08-26
 **/
@Data
public class RecruitmentInfo implements Serializable{

    // 编号
    private Integer id;

    // 名称
    private String name;

    // 方向
    private Integer directionId;

    // 类别
    private Integer typeId;

    // 公司
    private String company;

    // 地点
    private Integer addressId;

    // 职责
    private String responsibility;

    // 要求
    private String requirement;

    // 创建时间
    private Long createTime;

    public RecruitmentInfo() {
    }

}
