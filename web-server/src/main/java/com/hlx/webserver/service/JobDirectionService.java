package com.hlx.webserver.service;

import com.hlx.webserver.model.vo.JobDirectionDetailVO;
import com.hlx.webserver.model.po.JobDirection;

import java.util.List;

/**
 * @description: 工作方向服务层
 * @author: hlx 2018-08-26
 **/
public interface JobDirectionService {

    boolean save(JobDirection jobDirection);

    void deleteById(Integer jobDirectionId);

    List<JobDirection> listSimple();

    List<JobDirectionDetailVO> listDetail();

}
