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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult UserRegist(TbUser user) {
        try {
            return userService.registUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult UserLogin(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        try {
            return userService.loginUser(user,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout")
    public String UserLogout( HttpServletRequest request, HttpServletResponse response) {
            userService.logoutUser(request,response);
        return "redirect:http://localhost:8082";
    }

    @RequestMapping(value = "/token/{token}",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String  callback) {
        TaotaoResult result;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result= TaotaoResult.build(500, e.getMessage());
        }
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }

}
