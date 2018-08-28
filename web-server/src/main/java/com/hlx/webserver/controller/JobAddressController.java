package com.hlx.webserver.controller;

import com.hlx.webserver.model.po.ApiResult;
import com.hlx.webserver.model.po.JobAddress;
import com.hlx.webserver.service.JobAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 工作地点控制层
 * @author: hlx 2018-08-26
 **/
@RestController
@RequestMapping("/jobAddress")
public class JobAddressController {

    private JobAddressService jobAddressService;

    @Autowired
    public JobAddressController(JobAddressService jobAddressService) {
        this.jobAddressService = jobAddressService;
    }

    @ApiOperation(value = "获取所有工作地点")
    @GetMapping("/all")
    public ApiResult<List<JobAddress>> getAll() {
        ApiResult<List<JobAddress>> apiResult = new ApiResult<>();
        List<JobAddress> jobAddressList = jobAddressService.list();
        apiResult.setData(jobAddressList);
        return apiResult;
    }

    @ApiOperation(value = "新增工作地点")
    @PostMapping("/add")
    public ApiResult<String> add(@RequestBody JobAddress jobAddress) {
        ApiResult<String> apiResult = new ApiResult<>();
        boolean addSuccess = jobAddressService.save(jobAddress);
        if (!addSuccess) {
            apiResult.setStatus(404);
        }
        return apiResult;
    }

    @ApiOperation(value = "根据工作地点Id删除")
    @GetMapping("/delete/{jobAddressId}")
    public void deleteById(@PathVariable("jobAddressId") Integer jobAddressId) {
        jobAddressService.deleteById(jobAddressId);
    }

}
