package com.hr.controller;

import com.hr.entity.Comments;
import com.hr.entity.News;
import com.hr.entity.Topic;
import com.hr.service.CommentsService;
import com.hr.service.NewsService;
import com.hr.service.TopicService;
import com.hr.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {


    @Autowired
    private TopicService topicService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private Environment environment;
    @Autowired
    CommentsService commentsService;
    @GetMapping("/")
    public String Index(Model model){
        List<Topic> topics = topicService.queryAllTopics();
//        List<News> newsList = newsService.queryAllNews();
      //  String guonei = environment.getProperty("guonei");
//        String guowai = environment.getProperty("guowai");
//        System.out.println(guowai);
//        String yule = environment.getProperty("yule");
//        if(tid == null){
//            newsList = newsService.queryAllNews();
//        }else {
//            newsList = newsService.queryNewsByTid(tid);
//        }
        List<News> newsList1 = newsService.queryNewsByTname("国内");
        List<News> newsList2 = newsService.queryNewsByTname("国际");
        List<News> newsList3 = newsService.queryNewsByTname("娱乐");
//        model.addAttribute("newsList",newsList);
        model.addAttribute("topics",topics);
        model.addAttribute("newsList1",newsList1);
        model.addAttribute("newsList2",newsList2);
        model.addAttribute("newsList3",newsList3);
        return "index";
    }
    @GetMapping("/index/topicnews/{tid}")
    @ResponseBody
    public Object topicNews(@PathVariable("tid") Long tid ){
        List<News> topicNewsList = newsService.queryNewsByTid(tid);
        //model.addAttribute("topicNewsList",topicNewsList);
        return topicNewsList;
    }
    @GetMapping("/index/readnews/{nid}")
    public String readNews(@PathVariable("nid") Long nid,Model model){
        News news = newsService.queryNewsByNid(nid);
        model.addAttribute("news",news);
        return "readnews";
    }
    @PostMapping("/index/addcomments")
    public String addComments(Comments comments){
        commentsService.publishComments(comments);
        return "redirect:/index/readnews/"+comments.getCnid();
    }
    @GetMapping("/index/pagenews")
    @ResponseBody
    public Object pageNews(@RequestParam(value = "pageNo" , required = false) Integer pageNo){
        if(pageNo==null ||pageNo<1){
            pageNo=1;
        }
        Page page = newsService.queryNewsByPage(pageNo);
        System.out.println(pageNo);
        return page;
    }

}