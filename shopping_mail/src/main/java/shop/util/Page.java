package shop.util;

import java.util.List;

/**
 * 分装分页的信息
 * Created by near on 2016/3/3.
 */
public class Page<T> {

    /**
     * 当前页数
     */
    private Integer pageNums;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 每页大小（记录数）
     */
    private Integer pageSize;

    /**
     * 当前页存放的对象集合
     */
    private List<T> objs;

    public Page() {
    }

    public Integer getPageNums() {
        return pageNums;
    }

    public void setPageNums(Integer pageNums) {
        this.pageNums = pageNums;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
    }
}
