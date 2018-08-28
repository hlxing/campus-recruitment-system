package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.JobDirectionDao;
import com.hlx.webserver.model.vo.JobDirectionDetailVO;
import com.hlx.webserver.model.po.JobDirection;
import com.hlx.webserver.model.po.JobType;
import com.hlx.webserver.service.JobDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 工作方向服务实现层
 * @author: hlx 2018-08-26
 **/
@Service
public class JobDirectionServiceImpl implements JobDirectionService{

    private JobDirectionDao jobDirectionDao;

    @Autowired
    public JobDirectionServiceImpl(JobDirectionDao jobDirectionDao) {
        this.jobDirectionDao = jobDirectionDao;
    }

    @Override
    public boolean save(JobDirection jobDirection) {
        String name = jobDirection.getName();
        JobDirection test = jobDirectionDao.getByName(name);
        if (test == null) {
            jobDirectionDao.save(jobDirection);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Integer jobDirectionId) {
        jobDirectionDao.deleteById(jobDirectionId);
    }

    /**
     *  列出工作方向的简要信息(不包含该工作方向的所有工作类别)
     */
    @Override
    public List<JobDirection> listSimple() {
        return jobDirectionDao.list();
    }


    /**
     *  列出工作方向的详细信息(包括该工作方向的所有工作类别)
     */
    @Override
    public List<JobDirectionDetailVO> listDetail() {
        List<JobDirectionDetailVO> jobDirectionDetailVOS = new ArrayList<>();
        List<JobDirection> jobDirections = jobDirectionDao.list();
        for (JobDirection jobDirection : jobDirections) {
            List<JobType> jobTypes = jobDirectionDao.getJobTypeByDirectionId(jobDirection.getId());
            JobDirectionDetailVO jobDirectionDetailVO = new JobDirectionDetailVO();
            jobDirectionDetailVO.setId(jobDirection.getId());
            jobDirectionDetailVO.setName(jobDirection.getName());
            jobDirectionDetailVO.setJobTypes(jobTypes);
            jobDirectionDetailVOS.add(jobDirectionDetailVO);
        }
        return jobDirectionDetailVOS;
    }

}
