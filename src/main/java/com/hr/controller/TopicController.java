package com.hr.controller;

import com.hr.entity.Topic;
import com.hr.service.NewsService;
import com.hr.service.TopicService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/23 8:38
 */
@Controller
@RequestMapping("/topic/")
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    NewsService newsService;

    @GetMapping("list")
    public String list(Model model){
        List<Topic> topics = topicService.queryAllTopics();
        model.addAttribute("topics",topics);
        return "admin/topic_list";

    }
    @GetMapping("toadd")
    public String Toadd(){
        return "admin/topic_add";
    }
    @PostMapping("doadd")
    public String doAdd(String tname,Model model){
        if (topicService.queryTopicByTname(tname)==null){
            Topic topic = new Topic();
            topic.setTname(tname);
            Topic topic1 = topicService.addTopic(topic);
            System.out.println(topic1);
            }else {
            model.addAttribute("error","该主题已存在");
            model.addAttribute("tname",tname);
            return "admin/topic_add";
        }

        return "redirect:/topic/list";
    }
    @GetMapping("toupdate")
    public String toUpdate(Long tid,Model model){
        Topic topic = topicService.queryTopicByTid(tid);
        model.addAttribute("topic",topic);
        return "admin/topic_modify";
    }
    @PostMapping("doupdate")
    public String doUpdate(Topic topic ,Model model){
        if (topicService.queryTopicByTname(topic.getTname())==null) {


            topicService.updateTopic(topic);
            return "redirect:/topic/list";
        }else {
            model.addAttribute("error","该主题已存在");
            model.addAttribute("topic",topic);
            return "admin/topic_modify";
        }

    }
    @GetMapping("delete")
    public String delete(Long tid,Model model){
        //if (newsService.isNewsExists(tid)){
    //        model.addAttribute("error","存在有新闻");
       //}else {
            topicService.deleteTopic(tid);
//        }

        return "forward:/topic/list";
    }
    @GetMapping("newsexists")
    @ResponseBody
    public Object isNewsExists(Long tid){
        boolean newsExists = newsService.isNewsExists(tid);
        return "{\"result\":\""+newsExists+"\"}";
    }
}