package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.RecruitmentInfo;
import com.hlx.webserver.model.query.RecruitmentInfoQuery;

import java.util.List;

/**
 * @description: 招聘信息DAO
 * @author: hlx 2018-08-26
 **/
public interface RecruitmentInfoDao {

    void save(RecruitmentInfo recruitmentInfo);

    void deleteById(Integer recruitmentInfoId);

    void update(RecruitmentInfo recruitmentInfo);

    List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery recruitmentInfoQuery);

}
