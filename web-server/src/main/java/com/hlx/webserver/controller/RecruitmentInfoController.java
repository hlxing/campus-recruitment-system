package com.hlx.webserver.controller;

import com.hlx.webserver.model.dto.RecruitmentInfoAddDTO;
import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.model.po.RecruitmentInfo;
import com.hlx.webserver.model.query.RecruitmentInfoQuery;
import com.hlx.webserver.service.RecruitmentInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 招聘信息控制层
 * @author: hlx 2018-08-26
 **/
@RestController
@RequestMapping("/recruitmentInfo")
public class RecruitmentInfoController {

    private RecruitmentInfoService recruitmentInfoService;

    @Autowired
    public RecruitmentInfoController(RecruitmentInfoService recruitmentInfoService) {
        this.recruitmentInfoService = recruitmentInfoService;
    }

    @ApiOperation(value = "新增招聘信息")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody RecruitmentInfoAddDTO addDTO) {
        ApiResult<String> apiResult = new ApiResult<>();
        boolean addSuccess = recruitmentInfoService.save(addDTO);
        if (!addSuccess) {
            apiResult.setStatus(404);
        }
        return apiResult;
    }

    @ApiOperation(value = "更新招聘信息")
    @PostMapping("/update")
    public void update(@RequestBody RecruitmentInfo recruitmentInfo) {
        recruitmentInfoService.update(recruitmentInfo);
    }

    @ApiOperation(value = "根据招聘信息id删除")
    @ApiImplicitParam(name = "recruitmentInfoId", value = "招聘信息id")
    @GetMapping("/delete/{recruitmentInfoId}")
    public void deleteById(@PathVariable("recruitmentInfoId") Integer recruitmentInfoId) {
        recruitmentInfoService.deleteById(recruitmentInfoId);
    }

    @ApiOperation(value = "查询招聘信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "directionId", value = "方向id"),
            @ApiImplicitParam(name = "typeId", value = "类别id"),
            @ApiImplicitParam(required = true, name = "pageNum", value = "页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示数量")
    })
    @GetMapping("/query")
    public ApiResult<List<RecruitmentInfo>> getByQuery(
            @RequestParam("directionId") Integer directionId, @RequestParam("typeId") Integer typeId,
            @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        RecruitmentInfoQuery recruitmentInfoQuery = new RecruitmentInfoQuery();
        ApiResult<List<RecruitmentInfo>> apiResult = new ApiResult<>();
        // 页数和每页显示数量必填
        if (pageNum == null || pageSize == null) {
            apiResult.setStatus(404);
            return apiResult;
        }
        recruitmentInfoQuery.setPageNum(pageNum);
        recruitmentInfoQuery.setPageSize(pageSize);
        if (directionId != null) {
            recruitmentInfoQuery.setDirectionId(directionId);
        }
        if(typeId != null){
            recruitmentInfoQuery.setTypeId(typeId);
        }
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoService.
                listByQuery(recruitmentInfoQuery);
        apiResult.setData(recruitmentInfoList);
        return apiResult;
    }


}
