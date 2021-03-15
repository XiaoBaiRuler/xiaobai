package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Comment;

import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/21 0:09
 */
public interface CommentService {

    /**
     * 通过博客id获取评论内容
     *
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);

}
