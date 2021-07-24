package com.hr.dao;

import com.hr.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TopicDao extends CrudRepository<Topic, Long> {

    List<Topic> getTopicsBy();


    Topic queryTopicByTid(Long tid);

    Topic queryTopicByTname(String tname);


   // Topic deleteByTid(Long tid);

    @Override
    void delete(Topic topic);
}