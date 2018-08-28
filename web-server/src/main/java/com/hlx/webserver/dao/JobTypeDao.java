package com.hlx.webserver.dao;

import com.hlx.webserver.model.po.JobType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description: 工作类别Dao
 * @author: hlx 2018-08-26
 **/
public interface JobTypeDao {

    void save(JobType jobType);

    // 保存方向_类别关联表
    void saveDirectionType(@Param("jobDirectionId") Integer jobDirectionId, @Param("jobTypeId") Integer jobTypeId);

    void deleteById(Integer jobTypeId);

    List<JobType> list();

    JobType getByName(String name);

    JobType getById(Integer id);

    List<JobType> listByJobDirectionId(Integer jobDirectionId);

}
