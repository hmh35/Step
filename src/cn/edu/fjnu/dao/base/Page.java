package cn.edu.fjnu.dao.base;


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
