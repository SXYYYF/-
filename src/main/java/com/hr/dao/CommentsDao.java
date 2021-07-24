package com.hr.dao;

import com.hr.entity.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/19 9:56
 */
public interface CommentsDao extends CrudRepository<Comments,Long> {
    List<Comments> getAllByCnidOrderByCdateDesc(Long cnid);
    //public Comments addComments
    //删除指定新闻的评论
    void deleteByCnid(Long nid);
}
