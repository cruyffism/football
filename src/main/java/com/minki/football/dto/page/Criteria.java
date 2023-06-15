package com.minki.football.dto.page;

import lombok.Data;

@Data
public class Criteria {
    private int page;
    private int perPageNum;

    private String noticeSearchText;

    private String billingStartDate;

    private String billingEndDate;

    private String sortColumn;

    private String sortType;

    private String sort;

    private String idx;

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    public String getSort(){
        return this.sortColumn+ " " + this.sortType;
    }
}
