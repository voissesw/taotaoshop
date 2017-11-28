package com.voissesw.rest.service.impl;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * Created by hasee on 2017/11/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisCluster jedisCluster;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Override
    public TaotaoResult contentCacheSync(Long  contentId) {
        try {
            jedisCluster.hdel(INDEX_CONTENT_REDIS_KEY, contentId+"");
        }catch (Exception e){
            return TaotaoResult.build(500,"清除缓存失败");
        }
        return TaotaoResult.ok();
    }
}
