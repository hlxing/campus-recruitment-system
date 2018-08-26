package com.hlx.webserver.service;

import com.hlx.webserver.model.po.RecruitmentInfo;
import com.hlx.webserver.model.query.RecruitmentInfoQuery;

import java.util.List;

/**
 * @description: 招聘信息服务接口
 * @author: hlx 2018-08-26
 **/
public interface RecruitmentInfoService {

    boolean save(RecruitmentInfo recruitmentInfo);

    void deleteById(Integer recruitmentInfoId);

    void update(RecruitmentInfo recruitmentInfo);

    List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery recruitmentInfoQuery);

}
