package com.hongguo.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.hongguo.cloud.api.PayFeignApi;
import com.hongguo.cloud.entities.PayDTO;
import com.hongguo.cloud.response.ReturnData;
import com.hongguo.cloud.response.enums.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign")
public class CustomerOrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/pay/add")
    public ReturnData<String> addPay(@RequestBody PayDTO pay) {
        return payFeignApi.addPay(pay);
    }

    @GetMapping("/pay/get/{id}")
    public ReturnData<PayDTO> getPay(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");

        ReturnData resultData;
        try {
            System.out.println("调用开始-----:" + DateUtil.now());
            resultData = payFeignApi.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:" + DateUtil.now());
            resultData = ReturnData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }
}
