package com.hongguo.cloud.controller;

import com.hongguo.cloud.api.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.micrometer(id);
    }
}
