package com.hongguo.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.hongguo.cloud.api.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/feign")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-service-payment", fallbackMethod = "circuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.circuit(id);
    }

    //circuitFallback 就是服务降级后的兜底处理方法
    public String circuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "circuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~" + IdUtil.fastSimpleUUID();
    }

    @GetMapping(value = "/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-service-payment", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String bulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.bulkhead(id);
    }

    //bulkheadFallback 就是服务降级后的兜底处理方法
    public String bulkheadFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "circuitFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~" + IdUtil.fastSimpleUUID();
    }

    @GetMapping(value = "/pay/bulkhead/pool/{id}")
    @Bulkhead(name = "cloud-service-payment", fallbackMethod = "bulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> bulkheadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "------> enter");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "------> execute");

        return CompletableFuture.supplyAsync(() -> payFeignApi.bulkhead(id));
    }

    //bulkheadFallback 就是服务降级后的兜底处理方法
    public CompletableFuture<String> bulkheadPoolFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return CompletableFuture.supplyAsync(() -> "circuitFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~" + IdUtil.fastSimpleUUID());
    }

    @GetMapping(value = "/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-service-payment", fallbackMethod = "rateLimitFallback")
    public String ratelimit(@PathVariable("id") Integer id) {
        return payFeignApi.ratelimit(id);
    }

    public String rateLimitFallback(Integer id, Throwable t) {
        return "你被限流了，请稍后再试-----/(ㄒoㄒ)/~~";
    }
}
