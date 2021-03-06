package com.voissesw.service.impl;

import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbItemParamItemMapper;
import com.voissesw.mapper.TbItemParamMapper;
import com.voissesw.pojo.TbItemParam;
import com.voissesw.pojo.TbItemParamExample;
import com.voissesw.pojo.TbItemParamItem;
import com.voissesw.pojo.TbItemParamItemExample;
import com.voissesw.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/11/16.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper ItemParamMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TaotaoResult selectItemParamByCid(long cid){
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(cid);
        List<TbItemParam> list = ItemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        Date date = new Date();
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        ItemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult selectItemParamByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

}
