package com.minki.football.dto.page;

import lombok.Data;

@Data
public class PageMaker {
    private int totalCount;
    private int startPage;
    private int maxPage;
    private int endPage;
    private int displayPageNum = 6;
    public Criteria criteria;

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        startPage = ((criteria.getPage() - 1) / displayPageNum) * displayPageNum + 1;
        endPage = startPage + displayPageNum - 1;
        maxPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if (endPage > maxPage) {
            endPage = maxPage;
        }
    }
}
