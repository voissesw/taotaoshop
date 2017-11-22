package com.voissesw.service.impl;

import com.voissesw.common.generic.GenericService;
import com.voissesw.common.pojo.TaotaoResult;
import com.voissesw.pojo.TbContentCategory;

import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */
public interface ContentCategoryService extends GenericService<TbContentCategory,Long> {
    List<TbContentCategory> selectContentCategoryByParentId(long parentId);

    TaotaoResult insertContentCategory(long parentId, String name);
}
