package com.hongguo.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer/application")
public class ApplicationInfoController {

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    private static final String PAY_URL = "http://cloud-service-payment";

    @GetMapping("/hongguo/info")
    public String getInfo() {
        return restTemplate.getForObject(PAY_URL + "/application/hongguo/info", String.class);
    }

    @GetMapping("/discovery")
    public String getDiscovery() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-service-payment");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "\t" +
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri());
        }

        return instances.get(0).getHost() + ":" + instances.get(0).getPort();
    }
}
