package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/21 0:12
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 通过博客id获取所有一级评论
     *
     * @param blogId
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId);

    /**
     * 通过评论id查找评论
     *
     * @param parentCommentId
     * @return
     */
    @Query("select c from Comment c where c.id = ?1")
    Comment findByParentCommentId(Long parentCommentId);

}
