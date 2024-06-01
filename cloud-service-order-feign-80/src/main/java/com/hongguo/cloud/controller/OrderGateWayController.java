package com.hongguo.cloud.controller;

import com.hongguo.cloud.api.PayFeignApi;
import com.hongguo.cloud.response.ReturnData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class OrderGateWayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public ReturnData getById(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @GetMapping(value = "/pay/gateway/info")
    public ReturnData<String> getGatewayInfo() {
        return payFeignApi.getGatewayInfo();
    }
}
