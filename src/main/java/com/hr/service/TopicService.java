package com.hr.service;

import com.hr.entity.Topic;

import java.util.List;


public interface TopicService {

    /*
    * 查询所有主题
    * @return
    *
    * */

    public List<Topic> queryAllTopics();

    public Topic addTopic(Topic topic);

    Topic queryTopicByTid(Long tid);

    Topic updateTopic(Topic topic);
    //查询主体是否存在
    Topic queryTopicByTname(String tname);

    void deleteTopic(Long tid);
    //Topic deleteTopicByTid()
}