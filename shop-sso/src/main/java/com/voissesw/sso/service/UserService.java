package com.voissesw.sso.service;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbUser;

public interface UserService {
    public TaotaoResult CheckData(String content, int type);

    TaotaoResult registUser(TbUser user);

    TaotaoResult loginUser(TbUser user);

    TaotaoResult getUserByToken(String token);
}
