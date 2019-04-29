package com.rbsn.schedule.order;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class OrderController {

    @Value("${test}")
    private String name;

    @RequestMapping(value="call",method = RequestMethod.GET)
    public JSONObject call(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",name);
        return jsonObject;
    }
}
