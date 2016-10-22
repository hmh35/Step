package cn.edu.fjnu.dao.base;

/**
 * @Author: linqiu
 * @Date: 2016/3/17 13:38
 * @Description: 简单分页查询
 */
public class Page {
    private Integer startIndex;
    private Integer pageSize;

    public Page() {
    }

    public Page(Integer startIndex, Integer pageSize) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
