package com.rbsn.schedule.gateway;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;

    private final String serviceUrl = "http://web-order";

    @GetMapping("/call")
    public JSONObject call() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hello xueting!");
        return jsonObject;
    }

    /**
     * 调用注册到eureka的订单服务
     *
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getOrder() {
        String result = restTemplate.getForObject(serviceUrl + "/call", String.class);
        return result;
    }
}
