package com.hongguo.cloud.controller;

import com.hongguo.cloud.entities.Pay;
import com.hongguo.cloud.entities.PayDTO;
import com.hongguo.cloud.response.ReturnData;
import com.hongguo.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付 CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水方法")
    public ReturnData<String> add(@RequestBody Pay pay) {
        int result = payService.add(pay);
        return ReturnData.success("add success, result:" + result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ReturnData<String> delete(@PathVariable("id") Integer id) {
        int result = payService.delete(id);
        return ReturnData.success("delete success, result:" + result);

    }

    @PutMapping("/update")
    @Operation(summary = "更新", description = "更新支付流水方法")
    public ReturnData<String> update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int result = payService.update(pay);
        return ReturnData.success("update success, result:" + result);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "查询单条", description = "查询单条支付流水方法")
    public ReturnData<Pay> get(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能是负数");
        }
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnData.success(payService.queryById(id));
    }

    @GetMapping("/getAll")
    @Operation(summary = "查询全量", description = "查询全量支付流水方法")
    public ReturnData<List<Pay>> getAll() {
        return ReturnData.success(payService.queryAll());
    }
}
