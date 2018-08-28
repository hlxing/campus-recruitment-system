package com.hlx.webserver.controller;

import com.hlx.webserver.model.dto.JobTypeAddDTO;
import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.model.po.JobType;
import com.hlx.webserver.service.JobTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 工作类别控制层
 * @author: hlx 2018-08-26
 **/
@RestController
@RequestMapping("/jobType")
public class JobTypeController {

    private JobTypeService jobTypeService;

    @Autowired
    public JobTypeController(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @ApiOperation("添加某工作方向下的工作类别")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody JobTypeAddDTO addDTO) {
        ApiResult<String> apiResult = new ApiResult<>();
        boolean addSuccess = jobTypeService.save(addDTO);
        if (!addSuccess) {
            apiResult.setStatus(404);
        }
        return apiResult;
    }

    @ApiOperation(value = "根据id删除工作类别")
    @ApiImplicitParam(name = "id", value = "工作类别id")
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        jobTypeService.deleteById(id);
    }

    @ApiOperation(value = "获取所有工作类别")
    @GetMapping("/all")
    public ApiResult<List<JobType>> getAll() {
        ApiResult<List<JobType>> apiResult = new ApiResult<>();
        List<JobType> jobTypes = jobTypeService.list();
        apiResult.setData(jobTypes);
        return apiResult;
    }

    @ApiOperation(value = "根据工作方向id查询类型")
    @ApiImplicitParam(name = "jobDirectionId", value = "工作方向id")
    @GetMapping("/{jobDirectionId}")
    public ApiResult<List<JobType>> getByJobDirectionId(@PathVariable("jobDirectionId") Integer jobDirectionId) {
        ApiResult<List<JobType>> apiResult = new ApiResult<>();
        List<JobType> jobTypes = jobTypeService.listByJobDirectionId(jobDirectionId);
        apiResult.setData(jobTypes);
        return apiResult;
    }

}
