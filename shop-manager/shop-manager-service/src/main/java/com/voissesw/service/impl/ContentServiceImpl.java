package com.voissesw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voissesw.common.easyui.pojo.EUDataGridResult;
import com.voissesw.common.generic.GenericDao;
import com.voissesw.common.generic.GenericServiceImpl;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbContentMapper;
import com.voissesw.pojo.TbContent;
import com.voissesw.pojo.TbContentExample;
import com.voissesw.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */
@Service
public class ContentServiceImpl extends GenericServiceImpl<TbContent, Long> implements ContentService {

    @Autowired
    TbContentMapper tbContentMapper;


    @Override
    public GenericDao<TbContent, Long> getDao() {
        return tbContentMapper;
    }

    @Override
    public EUDataGridResult selectContentByCategoryId(long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = tbContentMapper.selectByExampleWithBLOBs(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(contents);
        PageInfo<TbContent> pageInfo = new PageInfo<>(contents);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
@Override

    public TaotaoResult inserTBContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insertSelective(tbContent);
        return TaotaoResult.ok();
    }


}
