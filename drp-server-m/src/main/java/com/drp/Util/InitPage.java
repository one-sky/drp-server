package com.drp.Util;

public class InitPage {

    private Integer pageNum;

    private Integer pageSize;

    private Integer startIndex;

    //构造方法初始化pageNum，pageSize
    public InitPage(Integer pageNum, Integer pageSize) {

        // 如果不传入pageNum，意味着是首页，则pageSize不设置
        if(pageNum ==null || "".equals(pageNum)) {
            this.pageNum = 1;
            this.pageSize = 100000;
            this.startIndex = 0;
        }else { // 传入了pageNum，不设pageSize，默认10
            if(pageSize ==null || "".equals(pageSize)){
                this.pageSize = 10;
            }else{
                this.pageSize = pageSize;
            }
            this.pageNum = pageNum;

            // sql查询从 0 开始
            this.startIndex = this.pageSize * (this.pageNum-1);
        }

    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
