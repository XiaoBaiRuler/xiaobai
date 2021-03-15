package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 小白
 * @create 2020/4/22 21:21
 */
@Controller
public class ShowTimeController {

    @Autowired
    private BlogService blogService;

    /**
     * 获取按年份分组后的博客列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/time")
    public String toTime(Model model) {
        model.addAttribute("times", blogService.timeBlogs());
        return "show/time";
    }
}
