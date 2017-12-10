package com.voissesw.sso.service.impl;

import com.voissesw.common.cookie.CookieUtils;
import com.voissesw.common.json.JsonUtils;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbUserMapper;
import com.voissesw.pojo.TbUser;
import com.voissesw.pojo.TbUserExample;
import com.voissesw.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisCluster jedisCluster;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${REDIS_USER_SESSION_EXPIRE}")
    private int REDIS_USER_SESSION_EXPIRE;

    @Override
    public TaotaoResult CheckData(String content, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (1 == type) {
            criteria.andUsernameEqualTo(content);
        }
        if (2 == type) {
            criteria.andPhoneEqualTo(content);
        }
        if (3 == type) {
            criteria.andEmailEqualTo(content);
        }
        int i = tbUserMapper.countByExample(example);
        if (1 == i) {
            return TaotaoResult.ok(false);
        }
        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult registUser(TbUser user) {
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insertSelective(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult loginUser(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return TaotaoResult.build(400, "用户名错误");
        }
        TbUser tbUser = list.get(0);
        String md5passwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (!md5passwd.equals(tbUser.getPassword())) {
            return TaotaoResult.build(400, "密码错误");
        }
        tbUser.setPassword(null);
        String token = UUID.randomUUID().toString();
        String s = JsonUtils.objectToJson(tbUser);
        jedisCluster.set(REDIS_USER_SESSION_KEY + ":" + token, s);
        jedisCluster.expire(REDIS_USER_SESSION_KEY + ":" + token, REDIS_USER_SESSION_EXPIRE);

        CookieUtils.addCookie(response, "TT_TOKEN", token, "/", -1);
        return TaotaoResult.ok(token);
    }

    @Override
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookie(request, response, "TT_TOKEN");
        jedisCluster.del(REDIS_USER_SESSION_KEY + ":" + token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String s = jedisCluster.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(s)) {
            return TaotaoResult.build(400, "用户未登陆或者session已过期");
        }
        jedisCluster.expire(REDIS_USER_SESSION_KEY + ":" + token, REDIS_USER_SESSION_EXPIRE);
        return TaotaoResult.ok(JsonUtils.jsonToPojo(s, TbUser.class));
    }
}
