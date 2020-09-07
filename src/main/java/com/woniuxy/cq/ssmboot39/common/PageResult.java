package com.woniuxy.cq.ssmboot39.common;

import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> extends Result{
    /**
     * 总条数
     */
    private long totalCount;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 当前页
     */
    private long pageIdx;

    /**
     * 每页条数
     */
    private long pageSize;

    /**
     * 当前页内容
     */
    private List<T> content;

    public PageResult(boolean success, String code, String message, long totalCount, long pageIdx, long pageSize, List<T> content) {
        super(success, code, message, null);
        this.totalCount = totalCount;
        this.pageIdx = pageIdx;
        this.pageSize = pageSize;
        this.content = content;
        this.totalPage = totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1;
    }


    public static PageResult success(long totalCount, long pageIdx, long pageSize, List content){
        return new PageResult(true,"SUCCESS","查询成功",totalCount,pageIdx,pageSize,content);
    }
}
