package com.voissesw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hasee on 2017/11/9.
 */
@Controller
public class PageController {

    @RequestMapping(value = {"/"})
    public String index(){
        return "index";
    }
    @RequestMapping(value = {"/{page}"})
    public String index(@PathVariable String page){
        return page;
    }

}
