package com.hr.service;

import com.hr.entity.News;
import com.hr.util.Page;

import java.util.List;

public interface NewsService {
    /*
    * 查询所有新闻
    * */


     List<News> queryAllNews();


     List<News> queryNewsByTname(String name);

     List<News> queryNewsByTid(Long tid);

     News queryNewsByNid(Long nid);
     //分页查询
     Page queryNewsByPage(int pageNo);
     //使用springdata jpa实现分页查询
     org.springframework.data.domain.Page queryJpaPageNews(int pageNo);

     boolean isNewsExists(Long ntid);

     //通过nid查询新闻
     News queryNewsBynid(Long nid);
//修改新闻
     News updateNews(News news);
     //添加新闻
     News addNews(News news);
//伤处指定新闻及该新闻下的所有评论
     void deleteNewsByNid(Long nid);

}
