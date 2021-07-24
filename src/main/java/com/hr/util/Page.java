package com.hr.util;

import com.hr.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/19 15:13
 * @description:封装每页的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private Integer pageNo;//当前页号
    private Integer pageSize=5;//页面大小
    private Integer totalCount;//总记录数
    private Integer totalPage;//总页数

    private List<News> newsList;//每页的数据


    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        //计算总页数
        this.totalPage = (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize+1);
    }
}
