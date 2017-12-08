package com.voissesw.portal.service.impl;

import com.voissesw.common.httpclient.HttpClientUtil;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemDesc;
import com.voissesw.pojo.TbItemParamItem;
import com.voissesw.portal.pojo.ItemInfo;
import com.voissesw.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hasee on 2017/12/4.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;


    @Override
    public ItemInfo getItemBaseInfo(long id) {
        String s = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + "/" + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(s, ItemInfo.class);

        if (taotaoResult.getStatus()==200) {
            return (ItemInfo) taotaoResult.getData();
        }
        return null;
    }

    @Override
    public TbItemDesc getItemDesc(long id) {
        String s = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + "/" + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(s, TbItemDesc.class);

        if (taotaoResult.getStatus()==200) {
            return (TbItemDesc) taotaoResult.getData();
        }
        return null;
    }

    @Override
    public TbItemParamItem getItemParam(long id) {
        String s = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + "/" + id);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(s, TbItemParamItem.class);

        if (taotaoResult.getStatus()==200) {
            return (TbItemParamItem) taotaoResult.getData();
        }
        return null;
    }
}
