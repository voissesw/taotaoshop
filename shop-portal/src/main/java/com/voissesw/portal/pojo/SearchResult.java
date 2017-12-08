package com.voissesw.portal.pojo;

import java.util.List;

public class SearchResult {
    /**
     * 商品列表
     */
    private List<SearchItemPoJo> itemList;
    /**
     * 总计路数
     */
    private int  recorcCount;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 当前页
     */
    private int curPage;

    public List<SearchItemPoJo> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItemPoJo> itemList) {
        this.itemList = itemList;
    }

    public int getRecorcCount() {
        return recorcCount;
    }

    public void setRecorcCount(int recorcCount) {
        this.recorcCount = recorcCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
