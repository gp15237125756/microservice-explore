package com.rbsn.schedule.feign.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "web-order")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/call")
    JSONObject call();
}
