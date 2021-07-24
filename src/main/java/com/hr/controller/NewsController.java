package com.hr.controller;

import com.hr.entity.News;
import com.hr.entity.Topic;
import com.hr.service.NewsService;
import com.hr.service.TopicService;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/20 8:47
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    TopicService topicService;
    @GetMapping("/newsList")
    public String newsList(Model model,@RequestParam(required = false) Integer pageNo){
        if (pageNo==null){
            pageNo=1;
        }
        Page page =  newsService.queryJpaPageNews(pageNo);
        model.addAttribute("page",page);
        return "admin/admin";
    }
    @GetMapping("/toadd")
    public String toAdd(Model model){
        List<Topic> topics = topicService.queryAllTopics();
        model.addAttribute("topics",topics);
        return "admin/news_add";
    }
    @PostMapping("/doadd")
    public String doAdd(News news){
        News news1 = newsService.addNews(news);

        return "redirect:/news/newsList";
    }
    @GetMapping("/toupdate")
    public String toUpdate(Long nid,Model model){
        News news = newsService.queryNewsBynid(nid);
        List<Topic> topics = topicService.queryAllTopics();
        model.addAttribute("topics",topics);
        model.addAttribute("news",news);
        return "admin/news_modify";
    }
    @PostMapping("/doupdate")
    public String doUpdate(News news ,Model model){
        News news1 = newsService.updateNews(news);
        return "redirect:/news/newsList";
    }
    @GetMapping("/delete")
    public String delete(Long nid){
        newsService.deleteNewsByNid(nid);
       // topicService.deleteTopic(nid);
        return "redirect:/news/newsList";

    }
}
