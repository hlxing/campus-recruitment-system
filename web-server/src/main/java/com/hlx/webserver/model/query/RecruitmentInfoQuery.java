package com.hlx.webserver.model.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 招聘信息查询对象
 * @author: hlx 2018-08-26
 **/
@Data
public class RecruitmentInfoQuery implements Serializable{

    // 方向
    private Integer directionId;

    // 类别
    private Integer typeId;

    // 页数
    private Integer pageNum;

    // 每页显示数量
    private Integer pageSize;

    public RecruitmentInfoQuery() {
    }

}
