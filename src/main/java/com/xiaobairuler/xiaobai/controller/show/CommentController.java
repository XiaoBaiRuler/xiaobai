package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.po.Comment;
import com.xiaobairuler.xiaobai.po.User;
import com.xiaobairuler.xiaobai.service.BlogService;
import com.xiaobairuler.xiaobai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author 小白
 * @create 2020/4/21 0:03
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * 浏览者头像
     */
    @Value("${comment.otherAvater}")
    private String otherAvater;

    /**
     * 管理员头像
     */
    @Value("${comment.adminAvater}")
    private String adminAvater;

    /**
     * 通过id获取博客的评论列表
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(id));
        return "show/blog :: commentList";
    }

    /**
     * 添加评论
     *
     * @param comment
     * @param session 判断用户是否登陆，区分是否是博主的评论
     * @return
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvater(adminAvater);
            comment.setNickname(user.getNickname());
            comment.setAdminComment(true);
        } else {
            comment.setAdminComment(false);
            comment.setAvater(otherAvater);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
