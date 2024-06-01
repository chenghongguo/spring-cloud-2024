package com.hongguo.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.hongguo.cloud.entities.Pay;
import com.hongguo.cloud.response.ReturnData;
import com.hongguo.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayGateWayController {
    @Resource
    PayService payService;

    @GetMapping(value = "/gateway/get/{id}")
    public ReturnData<Pay> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.queryById(id);
        return ReturnData.success(pay);
    }

    @GetMapping(value = "/gateway/info")
    public ReturnData<String> getGatewayInfo() {
        return ReturnData.success("gateway info test：" + IdUtil.simpleUUID());
    }
}
