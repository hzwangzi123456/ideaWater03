package com.common.util;

/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class PageUtil {

    //当前第几页
    private int page = 1;

    //每页多少行
    private int rows = 10;

    //总记录数
    private int total;

    //总页数
    private int pages;

    //当前页开始行
    private int pageStart;

    //当前页结束行
    private int pageEnd;

    //排序名称
    private String sort;

    //排序方式
    private String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        pages = total / rows;
        if (total % rows > 0) {
            pages = pages + 1;
        }
        this.total = total;
        if (pages < page) {
            this.page = 1;
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageStart() {
        if (page > 1) {
            return (page - 1) * rows;
        } else {
            return 0;
        }
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        if (page > 1) {
            return rows * page;
        } else {
            return rows;
        }
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


}