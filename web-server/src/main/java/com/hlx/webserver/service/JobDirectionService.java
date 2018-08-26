package com.hlx.webserver.service;

import com.hlx.webserver.model.dto.req.JobDirectionDTO;
import com.hlx.webserver.model.dto.vo.JobDirectionDetailVO;
import com.hlx.webserver.model.po.JobDirection;

import java.util.List;

/**
 * @description: 工作方向服务层
 * @author: hlx 2018-08-26
 **/
public interface JobDirectionService {

    boolean save(JobDirectionDTO jobDirectionDTO);

    void deleteById(Integer jobDirectionId);

    List<JobDirection> listSimple();

    List<JobDirectionDetailVO> listDetail();

}
