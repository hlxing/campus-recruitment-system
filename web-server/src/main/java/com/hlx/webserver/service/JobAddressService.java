package com.hlx.webserver.service;

import com.hlx.webserver.model.po.JobAddress;

import java.util.List;

/**
 * @description: 工作地点服务接口
 * @author: hlx 2018-08-26
 **/
public interface JobAddressService {

    boolean save(JobAddress jobAddress);

    void deleteById(Integer id);

    List<JobAddress> list();
}
