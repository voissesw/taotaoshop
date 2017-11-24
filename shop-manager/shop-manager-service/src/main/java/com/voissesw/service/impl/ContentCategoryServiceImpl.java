package com.voissesw.service.impl;

import com.voissesw.common.generic.GenericDao;
import com.voissesw.common.generic.GenericServiceImpl;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.mapper.TbContentCategoryMapper;
import com.voissesw.pojo.TbContentCategory;
import com.voissesw.pojo.TbContentCategoryExample;
import com.voissesw.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */
@Service
public class ContentCategoryServiceImpl extends GenericServiceImpl<TbContentCategory,Long> implements ContentCategoryService {

    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name){
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        Date date = new Date();
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        tbContentCategoryMapper.insert(contentCategory);
        TbContentCategory parentContentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parentContentCategory.getIsParent()){
            parentContentCategory.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult deleteContentCategory(long id) {
        TbContentCategory deleteCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        if (deleteCategory==null){
            TaotaoResult.ok();
        }
        long parentId = deleteCategory.getParentId();
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        if (list == null ||  list.size() == 0) {
            TbContentCategory contentCategory = new TbContentCategory();
            contentCategory.setId(parentId);
            contentCategory.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        }

        return TaotaoResult.ok();
    }

    @Override
    public GenericDao<TbContentCategory, Long> getDao() {
        return tbContentCategoryMapper;
    }
}
