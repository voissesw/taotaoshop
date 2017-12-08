package com.voissesw.rest.service.impl;

import com.voissesw.common.json.JsonUtils;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbItemDescMapper;
import com.voissesw.mapper.TbItemMapper;
import com.voissesw.mapper.TbItemParamItemMapper;
import com.voissesw.pojo.*;
import com.voissesw.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Created by hasee on 2017/12/4.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private JedisCluster jedisCluster;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private int REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult getItemBaseInfo(Long id) {
        String redisKey = REDIS_ITEM_KEY + ":" + id + "base";
        TbItem item = null;
        try {
            String s = jedisCluster.get(redisKey);
            if (StringUtils.isNotBlank(s)) {
                item = JsonUtils.jsonToPojo(s, TbItem.class);
                return TaotaoResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        item = tbItemMapper.selectByPrimaryKey(id);
        if (null == item) {
            return TaotaoResult.build(400, ":没有这个商品");
        }
        setRedisCatch(redisKey, item);
        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDesc(Long id) {
        String redisKey = REDIS_ITEM_KEY + ":" + id + ":desc";
        TbItemDesc itemDesc = null;
        try {
            String s = jedisCluster.get(redisKey);
            if (StringUtils.isNotBlank(s)) {
                itemDesc = JsonUtils.jsonToPojo(s, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDescExample example = new TbItemDescExample();
        example.createCriteria().andItemIdEqualTo(id);
        List<TbItemDesc> list = tbItemDescMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            itemDesc = list.get(0);
            setRedisCatch(redisKey, itemDesc);
            return TaotaoResult.ok(itemDesc);
        }
        return TaotaoResult.build(400, "没有这个商品描述");
    }


    @Override
    public TaotaoResult getItemParam(Long id) {
        String redisKey = REDIS_ITEM_KEY + ":" + id + ":param";
        TbItemParamItem itemParam = null;
        try {
            String s = jedisCluster.get(redisKey);
            if (StringUtils.isNotBlank(s)) {
                itemParam = JsonUtils.jsonToPojo(s, TbItemParamItem.class);
                return TaotaoResult.ok(itemParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(id);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list !=null && list.size()>0) {
            itemParam = list.get(0);
            setRedisCatch(redisKey, itemParam);
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.build(400, "没有这个商品参数");
    }

    private void setRedisCatch(String redisKey, Object itemParam) {
        try {
            String value = JsonUtils.objectToJson(itemParam);
            jedisCluster.set(redisKey, value);
            jedisCluster.expire(redisKey, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
