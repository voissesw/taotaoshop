package com.voissesw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voissesw.common.easyui.pojo.DataGridResult;
import com.voissesw.common.generic.GenericDao;
import com.voissesw.common.generic.GenericServiceImpl;
import com.voissesw.mapper.TbItemMapper;
import com.voissesw.pojo.TbItem;
import com.voissesw.pojo.TbItemCatExample;
import com.voissesw.pojo.TbItemExample;
import com.voissesw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YC on 2017/11/6.
 */
@Service
public class ItemServiceImpl extends GenericServiceImpl<TbItem, Long> implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

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
    public DataGridResult selectItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        DataGridResult result = new DataGridResult();
        result.setRows(tbItems);
        PageInfo<TbItem> info = new PageInfo<>(tbItems);
        result.setTotal(info.getTotal());
        return result;
    }
}
