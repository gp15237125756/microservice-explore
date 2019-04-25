package com.rbsn.schedule.order;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("call")
    public JSONObject call(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","hello xuetu!");
        return jsonObject;
    }
}
