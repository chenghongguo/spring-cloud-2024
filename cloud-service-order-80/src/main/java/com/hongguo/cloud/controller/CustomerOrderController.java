package com.hongguo.cloud.controller;

import com.hongguo.cloud.entities.PayDTO;
import com.hongguo.cloud.response.ReturnData;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
public class CustomerOrderController {

    @Resource
    private RestTemplate restTemplate;
    private static final String PAY_URL = "http://cloud-service-payment";

    @PostMapping("/pay/add")
    public ReturnData<String> addPay(@RequestBody PayDTO pay) {
        return restTemplate.postForObject(PAY_URL + "/pay/add", pay, ReturnData.class);
    }

    @PutMapping("/pay/update")
    public ReturnData<String> updatePay(@RequestBody PayDTO pay) {
        HttpEntity<PayDTO> httpEntity = new HttpEntity<>(pay);
        ResponseEntity<ReturnData> exchange =
                restTemplate.exchange(PAY_URL + "/pay/update", HttpMethod.PUT, httpEntity, ReturnData.class);
        return exchange.getBody();
    }

    @DeleteMapping("/pay/delete/{id}")
    public ReturnData<String> deletePay(@PathVariable("id") Integer id) {
        HttpEntity<Integer> httpEntity = new HttpEntity<>(id);
        ResponseEntity<ReturnData> exchange =
                restTemplate.exchange(PAY_URL + "/pay/delete/" + id, HttpMethod.DELETE, httpEntity, ReturnData.class);
        return exchange.getBody();
    }

    @GetMapping("/pay/get/{id}")
    public ReturnData<PayDTO> getPay(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAY_URL + "/pay/get/" + id, ReturnData.class, id);
    }
}
