package com.voissesw.rest.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
@RequestMapping("/catch")
public class redisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/sync/{contentId}")
    @ResponseBody
    public TaotaoResult synccontentById(@PathVariable Long contentId) {
        return redisService.contentCacheSync(contentId);
    }

}
