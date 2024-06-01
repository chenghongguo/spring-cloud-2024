package com.hongguo.cloud.api;

import com.hongguo.cloud.entities.PayDTO;
import com.hongguo.cloud.response.ReturnData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-service-payment")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    ReturnData<String> addPay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    ReturnData get(@PathVariable("id") Integer id);

    @GetMapping("/application/hongguo/info")
    String getInfo();

    @GetMapping("/pay/circuit/{id}")
    String circuit(@PathVariable("id") Integer id);

    @GetMapping("/pay/bulkhead/{id}")
    String bulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    String ratelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    String micrometer(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/get/{id}")
    ReturnData getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info")
    ReturnData<String> getGatewayInfo();
}
