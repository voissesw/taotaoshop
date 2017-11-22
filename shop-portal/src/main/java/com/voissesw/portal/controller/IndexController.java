package com.voissesw.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YC on 2017/11/6.
 */
@Controller
public class IndexController {


    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }


}
