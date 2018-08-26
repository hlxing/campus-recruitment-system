package com.hlx.webserver.service.impl;

import com.hlx.webserver.dao.JobAddressDao;
import com.hlx.webserver.model.dto.req.JobAddressDTO;
import com.hlx.webserver.model.po.JobAddress;
import com.hlx.webserver.service.JobAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 工作地点服务实现层
 * @author: hlx 2018-08-26
 **/
@Service
public class JobAddressServiceImpl implements JobAddressService{

    private JobAddressDao jobAddressDao;

    @Autowired
    public JobAddressServiceImpl(JobAddressDao jobAddressDao) {
        this.jobAddressDao = jobAddressDao;
    }

    @Override
    public boolean save(JobAddressDTO jobAddressDTO) {
        String jobAddressName = jobAddressDTO.getName();
        boolean existTestSuccess = jobAddressDao.getByName(jobAddressName) != null;
        if (existTestSuccess) {
            jobAddressDao.save(jobAddressName);
        }
        return false;
    }

    @Override
    public void deleteById(Integer id) {
        jobAddressDao.deleteById(id);
    }

    @Override
    public List<JobAddress> list() {
        return jobAddressDao.list();
    }
}
