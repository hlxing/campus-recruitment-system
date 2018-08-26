package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.JobDirection;
import com.hlx.webserver.model.po.JobType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 工作方向Dao
 * @author: hlx 2018-08-26
 **/
@Repository
public interface JobDirectionDao {

    void save(String name);

    void deleteById(Integer jobDirectionId);

    List<JobDirection> list();

    JobDirection getByName(String name);

    JobDirection getById(Integer id);

    // 获取该方向的类别
    List<JobType> getJobTypeByDirectionId(Integer jobDirectionId);
}
