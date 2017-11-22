package com.voissesw.rest.service.impl;

import com.voissesw.mapper.TbItemCatMapper;
import com.voissesw.pojo.TbItemCat;
import com.voissesw.pojo.TbItemCatExample;
import com.voissesw.rest.pojo.CatNode;
import com.voissesw.rest.pojo.CatResult;
import com.voissesw.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatNode() {

        CatResult catResult = new CatResult();
        catResult.setData(getItemCatList(0));
        return catResult;
    }

    private List<?> getItemCatList(long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
        List resultList = new ArrayList<>();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
//            父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
                catNode.setItem(getItemCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count++;
                if (count>=14){
                    break;
                }
//                叶子节点
            } else {
                resultList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return resultList;
    }

}
