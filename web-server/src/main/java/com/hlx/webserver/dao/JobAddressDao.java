package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.JobAddress;

import java.util.List;

/**
 * @description: 工作地点DAO
 * @author: hlx 2018-08-26
 **/
public interface JobAddressDao {

    void save(String name);

    void deleteById(Integer id);

    List<JobAddress> list();

    JobAddress getByName(String name);

}
