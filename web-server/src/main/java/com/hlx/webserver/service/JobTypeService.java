package com.hlx.webserver.service;

import com.hlx.webserver.model.dto.req.JobTypeDTO;
import com.hlx.webserver.model.po.JobType;

import java.util.List;

/**
 * @description: 工作类别服务层
 * @author: hlx 2018-08-26
 **/
public interface JobTypeService {

    boolean save(Integer jobDirectionId, JobTypeDTO jobTypeDTO);

    void deleteById(Integer jobTypeId);

    List<JobType> list();

    List<JobType> listByJobDirectionId(Integer jobDirectionId);
}
