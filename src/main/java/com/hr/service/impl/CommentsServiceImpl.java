package com.hr.service.impl;

import com.hr.dao.CommentsDao;
import com.hr.entity.Comments;
import com.hr.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yaoyunfei
 * @data 2021/7/19 9:59
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsDao commentsDao;
    @Override
    public List<Comments> queryAllCommentsByNid(Long nid) {
        return commentsDao.getAllByCnidOrderByCdateDesc(nid);
    }

    @Override
    public Comments publishComments(Comments comments) {
        comments.setCdate(new Date());
        return commentsDao.save(comments);

    }
}
