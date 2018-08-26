package com.hlx.webserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlx.webserver.dao.JobDirectionDao;
import com.hlx.webserver.dao.JobTypeDao;
import com.hlx.webserver.dao.RecruitmentInfoDao;
import com.hlx.webserver.model.po.RecruitmentInfo;
import com.hlx.webserver.model.query.RecruitmentInfoQuery;
import com.hlx.webserver.service.RecruitmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @description: 招聘信息服务实现层
 * @author: hlx 2018-08-26
 **/
@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService{

    private RecruitmentInfoDao recruitmentInfoDao;

    private JobTypeDao jobTypeDao;

    private JobDirectionDao jobDirectionDao;

    @Autowired
    public RecruitmentInfoServiceImpl(RecruitmentInfoDao recruitmentInfoDao,
                                      JobTypeDao jobTypeDao, JobDirectionDao jobDirectionDao) {
        this.recruitmentInfoDao = recruitmentInfoDao;
        this.jobTypeDao = jobTypeDao;
        this.jobDirectionDao = jobDirectionDao;
    }

    @Override
    public boolean save(RecruitmentInfo recruitmentInfo) {
        // 检测方向和类别的合法性
        Integer jobDirectionId = recruitmentInfo.getDirectionId();
        Integer jobTypeId = recruitmentInfo.getTypeId();
        boolean directionTestSuccess = jobDirectionDao.getById(jobDirectionId) != null;
        boolean typeTestSuccess = jobTypeDao.getById(jobTypeId) != null;
        if (directionTestSuccess && typeTestSuccess) {
            recruitmentInfoDao.save(recruitmentInfo);
        }
        return false;
    }

    @Override
    public void deleteById(Integer recruitmentInfoId) {
        recruitmentInfoDao.deleteById(recruitmentInfoId);
    }

    @Override
    public void update(RecruitmentInfo recruitmentInfo) {
        recruitmentInfoDao.update(recruitmentInfo);
    }

    @Override
    public List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery recruitmentInfoQuery) {
        PageHelper.startPage(recruitmentInfoQuery.getPageNum(),recruitmentInfoQuery.getPageSize());
        return recruitmentInfoDao.listByQuery(recruitmentInfoQuery);
    }
}
