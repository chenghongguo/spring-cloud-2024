package com.hongguo.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
public class PayCircuitController {

    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能是负数");
        } else if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping("/bulkhead/{id}")
    public String bulkhead(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能是负数");
        } else if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, bulkhead! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping(value = "/ratelimit/{id}")
    public String ratelimit(@PathVariable("id") Integer id) {
        return "Hello, ratelimit欢迎到来 inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }
}
