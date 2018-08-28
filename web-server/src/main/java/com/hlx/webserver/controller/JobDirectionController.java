package com.hlx.webserver.controller;

import com.hlx.webserver.model.vo.JobDirectionDetailVO;
import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.model.po.JobDirection;
import com.hlx.webserver.service.JobDirectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @description: 工作方向控制层
 * @author: hlx 2018-08-26
 **/
@RestController
@RequestMapping("/jobDirection")
public class JobDirectionController {

    private JobDirectionService jobDirectionService;

    @Autowired
    public JobDirectionController(JobDirectionService jobDirectionService) {
        this.jobDirectionService = jobDirectionService;
    }

    @ApiOperation(value = "添加工作方向")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody JobDirection jobDirection) {
        ApiResult<String> apiResult = new ApiResult<>();
        boolean addSuccess = jobDirectionService.save(jobDirection);
        if (!addSuccess) {
            apiResult.setStatus(404);
        }
        return apiResult;
    }

    @ApiOperation(value = "根据id删除工作方向")
    @ApiImplicitParam(name = "id", value = "工作方向id")
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        jobDirectionService.deleteById(id);
    }

    @ApiOperation(value = "获取工作方向的简要信息(不包含方向中的类别)")
    @GetMapping("/all/simple")
    public ApiResult<List<JobDirection>> getAllSimple() {
        ApiResult<List<JobDirection>> apiResult = new ApiResult<>();
        List<JobDirection> jobDirections = jobDirectionService.listSimple();
        apiResult.setData(jobDirections);
        return apiResult;
    }

    @ApiOperation(value = "获取工作方向的详细信息(包含方向中的类别)")
    @GetMapping("/all/detail")
    public ApiResult<List<JobDirectionDetailVO>> getAllDetail() {
        ApiResult<List<JobDirectionDetailVO>> apiResult = new ApiResult<>();
        List<JobDirectionDetailVO> jobDirectionDetailVOS = jobDirectionService.listDetail();
        apiResult.setData(jobDirectionDetailVOS);
        return apiResult;
    }

}
