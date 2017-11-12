package com.voissesw.service.impl;

import com.voissesw.common.generic.GenericDao;
import com.voissesw.common.generic.GenericServiceImpl;
import com.voissesw.mapper.TbItemCatMapper;
import com.voissesw.pojo.TbItemCat;
import com.voissesw.pojo.TbItemCatExample;
import com.voissesw.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ItemCatServiceImpl extends GenericServiceImpl<TbItemCat,Long> implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public GenericDao<TbItemCat, Long> getDao() {
        return tbItemCatMapper;
    }

    @Override
    public List<TbItemCat> selectItemCatByParentId(Long parentId){
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return tbItemCatMapper.selectByExample(example);
    }


}
