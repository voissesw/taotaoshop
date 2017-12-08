package com.voissesw.sso.controller;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbUser;
import com.voissesw.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object UserCheck(@PathVariable String param, @PathVariable Integer type, String callback ) {
        TaotaoResult result;
        if (type != 1 && type != 2 && type != 3) {
            result= TaotaoResult.build(400, "校验类型有误");
        }else{
            try {
                result =  userService.CheckData(param, type);
            } catch (Exception e) {
                e.printStackTrace();
                result=  TaotaoResult.build(500, e.getMessage());
            }
        }
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult UserRegist(TbUser user) {
        try {
            return userService.registUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult UserLogin(TbUser user) {
        try {
            return userService.loginUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/token{token}",method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult getUserByToken(@PathVariable String token) {
        try {
            return userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }

}
