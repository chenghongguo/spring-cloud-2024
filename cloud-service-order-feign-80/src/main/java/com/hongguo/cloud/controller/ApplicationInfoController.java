package com.hongguo.cloud.controller;

import com.hongguo.cloud.api.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/application")
public class ApplicationInfoController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/hongguo/info")
    public String getInfo() {
        return payFeignApi.getInfo();
    }
}
