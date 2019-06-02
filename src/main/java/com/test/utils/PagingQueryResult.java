package com.test.utils;

import java.util.List;

public class PagingQueryResult {

    Long count;
    List pagingResult;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List getPagingResult() {
        return pagingResult;
    }

    public void setPagingResult(List pagingResult) {
        this.pagingResult = pagingResult;
    }
}
