package com.voissesw.rest.service;

import com.voissesw.pojo.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> selectContentList(long contentCid);
}
