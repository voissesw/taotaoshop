package com.voissesw.rest.service.impl;

import com.voissesw.mapper.TbContentMapper;
import com.voissesw.pojo.TbContent;
import com.voissesw.pojo.TbContentExample;
import com.voissesw.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public List<TbContent> selectContentList(long contentCid){
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> contents = contentMapper.selectByExample(example);
        return contents;
    }
}
