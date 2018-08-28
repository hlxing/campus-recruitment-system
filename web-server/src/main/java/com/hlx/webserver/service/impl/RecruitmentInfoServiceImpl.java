package com.hlx.webserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.hlx.webserver.dao.JobDirectionDao;
import com.hlx.webserver.dao.JobTypeDao;
import com.hlx.webserver.dao.RecruitmentInfoDao;
import com.hlx.webserver.model.dto.RecruitmentInfoAddDTO;
import com.hlx.webserver.model.po.RecruitmentInfo;
import com.hlx.webserver.model.query.RecruitmentInfoQuery;
import com.hlx.webserver.service.RecruitmentInfoService;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

    @Autowired
    public RecruitmentInfoServiceImpl(RecruitmentInfoDao recruitmentInfoDao,
                                      JobTypeDao jobTypeDao, JobDirectionDao jobDirectionDao,
                                      ModelMapper modelMapper) {
        this.recruitmentInfoDao = recruitmentInfoDao;
        this.jobTypeDao = jobTypeDao;
        this.jobDirectionDao = jobDirectionDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(RecruitmentInfoAddDTO addDTO) {
        // 检测方向和类别的合法性
        Integer jobDirectionId = addDTO.getDirectionId();
        Integer jobTypeId = addDTO.getTypeId();
        boolean directionTestSuccess = jobDirectionDao.getById(jobDirectionId) != null;
        boolean typeTestSuccess = jobTypeDao.getById(jobTypeId) != null;
        if (directionTestSuccess && typeTestSuccess) {
            // DTO转换为PO
            RecruitmentInfo recruitmentInfo = modelMapper.map(addDTO, RecruitmentInfo.class);
            // 补充信息
            recruitmentInfo.setCreateTime(System.currentTimeMillis());
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
