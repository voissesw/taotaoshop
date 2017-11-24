package com.voissesw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.generic.GenericDao;
import com.voissesw.common.generic.GenericServiceImpl;
import com.voissesw.common.pojo.ServiceException;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbItemDescMapper;
import com.voissesw.mapper.TbItemMapper;
import com.voissesw.mapper.TbItemParamItemMapper;
import com.voissesw.pojo.*;
import com.voissesw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by YC on 2017/11/6.
 */
@Service
public class ItemServiceImpl extends GenericServiceImpl<TbItem, Long> implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return GenericDao实现类
     */
    @Override
    public GenericDao<TbItem, Long> getDao() {
        return tbItemMapper;
    }

    @Override
    public EUDataGridResult selectItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(tbItems);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult insertItem(TbItem item, String itemDesc, String itemParam) {
        Date date = new Date();
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);
        tbItemMapper.insert(item);
        int result = insertItemDesc(item.getId(), itemDesc);
        if (result == 0) {
            throw new ServiceException();
        }
        result = insertItemParam(item.getId(), itemParam);
        if (result == 0) {
            throw new ServiceException();
        }
        return TaotaoResult.ok();
    }

    private int insertItemParam(long itemId, String itemParam) {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        Date date = new Date();
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        int result = tbItemParamItemMapper.insert(itemParamItem);
        return result;
    }

    private int insertItemDesc(long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        Date date = new Date();
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        int result = tbItemDescMapper.insert(itemDesc);
        return result;
    }
}
