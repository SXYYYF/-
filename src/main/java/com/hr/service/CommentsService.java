package com.hr.service;

import com.hr.entity.Comments;

import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/19 9:58
 */
public interface CommentsService {
    List<Comments> queryAllCommentsByNid(Long nid);
    //发布评论
    Comments publishComments(Comments comments);
}
