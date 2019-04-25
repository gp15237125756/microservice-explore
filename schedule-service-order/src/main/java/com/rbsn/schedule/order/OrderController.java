package com.rbsn.schedule.order;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@EnableAutoConfiguration
@RefreshScope
public class OrderController {

    @Value("${zuul.routes.dogs.url}")
    private String url;

    @GetMapping("call")
    public JSONObject call(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",url);
        return jsonObject;
    }
}
