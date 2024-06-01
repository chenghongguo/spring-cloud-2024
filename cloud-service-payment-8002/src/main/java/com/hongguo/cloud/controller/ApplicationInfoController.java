package com.hongguo.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationInfoController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hongguo/info")
    public String getHongguoInfo(@Value("${hongguo.info}") String hongguoInfo) {
        return "hongguo info:" + hongguoInfo + ", port:" + port;
    }
}
