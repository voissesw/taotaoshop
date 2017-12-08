package com.voissesw.portal.service.impl;

import com.voissesw.common.httpclient.HttpClientUtil;
import com.voissesw.common.json.JsonUtils;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.portal.pojo.SearchResult;
import com.voissesw.portal.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hasee on 2017/12/3.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String q, int page) {
        Map<String, String> map = new HashMap<>();
        map.put("q", q);
        map.put("page", page+"");
        try {
            String s = HttpClientUtil.doGet(SEARCH_BASE_URL, map);
            if (StringUtils.isBlank(s)){
                return null;
            }
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(s, SearchResult.class);
            if (taotaoResult !=null &&taotaoResult.getStatus()==200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
