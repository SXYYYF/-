package com.hr.service.impl;

import com.hr.dao.CommentsDao;
import com.hr.dao.NewsDao;
import com.hr.entity.Comments;
import com.hr.entity.News;
import com.hr.service.NewsService;
import com.hr.util.Contant;
import com.hr.util.Page;
import javassist.compiler.TokenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private CommentsDao commentsDao;

    @Override
    public List<News> queryAllNews() {
        List<News> newsList = newsDao.getAllByOrderByNcreateDateDesc();
        return newsList;
    }

    @Override
    public List<News> queryNewsByTname(String name) {
        //System.out.println(newsDao.getAllByName("国内"));
        return newsDao.getAllByName(name);

    }

    @Override
    public List<News> queryNewsByTid(Long tid) {
        return newsDao.getAllByNtidOrderByNcreateDateDesc(tid);
    }

    @Override
    public News queryNewsByNid(Long nid) {
        News news = newsDao.getNewsByNid(nid);
        List<Comments> commentsList = commentsDao.getAllByCnidOrderByCdateDesc(nid);
        news.setCommentsList(commentsList);
        return news;
    }

    @Override
    public Page queryNewsByPage(int pageNo) {
        Page page = new Page();

        int count = newsDao.countAllBy();
        page.setTotalCount(count);//总记录数
        page.setPageNo(pageNo);
//        System.out.println(count);
//        System.out.println(page.getPageNo());
        int index = (page.getPageNo() - 1) * page.getPageSize();
        List<News> newsList = newsDao.getNewsByPage(index, page.getPageSize());
        page.setNewsList(newsList);
        return page;
    }

    @Override
    public org.springframework.data.domain.Page queryJpaPageNews(int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo-1,Contant.PAGESIZE,Sort.by(Sort.Direction.DESC,"ncreateDate"));
        org.springframework.data.domain.Page<News> newsPage = newsDao.findAll(pageRequest);
        return newsPage;
    }

    @Override
    public boolean isNewsExists(Long ntid) {
        return newsDao.existsNewsByNtid(ntid);
    }

//修改新闻
    @Override
    public News updateNews(News news) {
        news.setNmodifyDate(new Date());
        return newsDao.save(news);
    }
//添加新闻
    @Override
    public News addNews(News news) {
        news.setNcreateDate(new Date());
        return newsDao.save(news);
    }

    @Override
    @Transactional//支持事务
    public void deleteNewsByNid(Long nid) {
        commentsDao.deleteByCnid(nid);
        newsDao.deleteById(nid);
    }


}