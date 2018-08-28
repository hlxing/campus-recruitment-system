package com.hlx.webserver.service;

import com.hlx.webserver.model.dto.JobTypeAddDTO;
import com.hlx.webserver.model.po.JobType;

import java.util.List;

/**
 * @description: 工作类别服务层
 * @author: hlx 2018-08-26
 **/
public interface JobTypeService {

    boolean save(JobTypeAddDTO jobTypeAddDTO);

    void deleteById(Integer jobTypeId);

    List<JobType> list();

    List<JobType> listByJobDirectionId(Integer jobDirectionId);
}
