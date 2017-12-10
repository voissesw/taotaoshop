package com.voissesw.sso.service;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public TaotaoResult CheckData(String content, int type);

    TaotaoResult registUser(TbUser user);


    TaotaoResult getUserByToken(String token);

    TaotaoResult loginUser(TbUser user, HttpServletRequest request, HttpServletResponse response);

    void logoutUser(HttpServletRequest request, HttpServletResponse response);
}
