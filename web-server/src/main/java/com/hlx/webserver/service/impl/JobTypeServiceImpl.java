package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.JobDirectionDao;
import com.hlx.webserver.dao.JobTypeDao;
import com.hlx.webserver.model.dto.JobTypeAddDTO;
import com.hlx.webserver.model.po.JobDirection;
import com.hlx.webserver.model.po.JobType;
import com.hlx.webserver.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 工作类别服务实现层
 * @author: hlx 2018-08-26
 **/
@Service
public class JobTypeServiceImpl implements JobTypeService {

    private JobTypeDao jobTypeDao;

    private JobDirectionDao jobDirectionDao;

    @Autowired
    public JobTypeServiceImpl(JobTypeDao jobTypeDao, JobDirectionDao jobDirectionDao) {
        this.jobTypeDao = jobTypeDao;
        this.jobDirectionDao = jobDirectionDao;
    }

    @Override
    public boolean save(JobTypeAddDTO addDTO) {
        // 检测工作方向和工作类别合法性
        // 工作方向id存在,工作类别名称不重复
        JobDirection jobDirection = jobDirectionDao.getById(addDTO.getDirectionId());
        JobType testType = jobTypeDao.getByName(addDTO.getName());
        if (jobDirection != null && testType == null) {
            // 为增加类别后获取自增ID,进一步转换为JobType
            JobType jobType = new JobType(addDTO.getName());
            jobTypeDao.save(jobType);
            jobTypeDao.saveDirectionType(addDTO.getDirectionId(), jobType.getId());
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Integer jobTypeId) {
        jobTypeDao.deleteById(jobTypeId);
    }

    @Override
    public List<JobType> list() {
        return jobTypeDao.list();
    }

    @Override
    public List<JobType> listByJobDirectionId(Integer jobDirectionId) {
        return jobDirectionDao.getJobTypeByDirectionId(jobDirectionId);
    }
}
