package com.rbsn.schedule.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.rbsn.schedule.feign.service.OrderClient;
import feign.Contract;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private OrderClient orderClient;

    /**
     * 调用注册到eureka的订单服务
     *
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public JSONObject getOrder() {
        return orderClient.call();
    }

    /**
     * feign可以自定义翻译器 编码解码器等属性
     *
     * @return
     */
    @RequestMapping(value = "/order/custom", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public JSONObject getOrder2() {
        return Feign.builder().contract(new SpringMvcContract()).decoder(new GsonDecoder()).target(OrderClient.class,"http://localhost:8081").call();
    }
}
