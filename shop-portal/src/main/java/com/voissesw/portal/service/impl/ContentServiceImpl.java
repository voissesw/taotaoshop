package com.voissesw.portal.service.impl;

import com.voissesw.common.httpclient.HttpClientUtil;
import com.voissesw.common.json.JsonUtils;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContent;
import com.voissesw.portal.service.ContentService;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ContentServiceImpl implements ContentService
{
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_BIGAD_URL}")
    private String REST_INDEX_BIGAD_URL;
@Override
    public String getContentList() {
        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_BIGAD_URL);
        try {
            TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
            List<TbContent> list = (List<TbContent>) taotaoResult.getData();
            List<Map> resultList = new ArrayList();
            for (TbContent tbContent : list) {
                Map map = new HashMap();
                map.put("src", tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("heightB", 552);
                map.put("widthB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt",tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
