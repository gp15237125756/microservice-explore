package com.rbsn.schedule.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    private final String serviceUrl = "http://web-order";

    /**
     * 调用注册到eureka的订单服务
     *
     * @return
     */
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getOrder() {
        return restTemplate.getForObject(serviceUrl + "/call", String.class);
    }

    public String defaultStores() {
        return "forward:/hystrix";
    }
}
