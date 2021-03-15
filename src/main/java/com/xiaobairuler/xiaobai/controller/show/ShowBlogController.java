package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 小白
 * @create 2020/4/22 21:10
 */
@Controller
public class ShowBlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 挑转到博客详情页面，并且已经转化md文件格式了
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndCovert(id));
        model.addAttribute("title", blogService.getBlog(id).getTitle());
        return "show/blog";
    }
}
