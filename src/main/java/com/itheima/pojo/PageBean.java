package com.itheima.pojo;

import java.util.List;

/**
 * @author Mendy
 * @create 2023-06-09 18:09
 */
public class PageBean<T> {

    private int totalCount;
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
