package com.voissesw.rest.service.impl;

import com.voissesw.common.json.JsonUtils;
import com.voissesw.mapper.TbContentMapper;
import com.voissesw.pojo.TbContent;
import com.voissesw.pojo.TbContentExample;
import com.voissesw.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Service
class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisCluster jedisCluster;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Override
    public List<TbContent> selectContentList(long contentCid){
        try {
            String catchString = jedisCluster.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
            if (StringUtils.isNotBlank(catchString)) {
                return JsonUtils.jsonToList(catchString,TbContent.class);
            }

        } catch (Exception e) {

        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> contents = contentMapper.selectByExample(example);

        try {
            String catchString = JsonUtils.objectToJson(contents);
            jedisCluster.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", catchString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
