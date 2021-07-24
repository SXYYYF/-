package com.hr.service.impl;

import com.hr.dao.TopicDao;
import com.hr.entity.Topic;
import com.hr.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    @Override
    public List<Topic> queryAllTopics() {
        List<Topic> topicList = topicDao.getTopicsBy();

        return topicList;
    }

    @Override
    public Topic addTopic(Topic topic) {
        return topicDao.save(topic);
    }

    @Override
    public Topic queryTopicByTid(Long tid) {
        return topicDao.queryTopicByTid(tid);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return topicDao.save(topic);
    }

    @Override
    public Topic queryTopicByTname(String tname) {
        return topicDao.queryTopicByTname(tname);
    }

    @Override
    public void deleteTopic(Long tid) {
        topicDao.deleteById(tid);
    }


}