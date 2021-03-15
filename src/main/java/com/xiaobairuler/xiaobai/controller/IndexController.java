package com.xiaobairuler.xiaobai.controller;

import com.xiaobairuler.xiaobai.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;


    /**
     * 跳转到首页
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index", "/"})
    public String toIndex(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        return "index";
    }

    @PostMapping("/search")
    public String toSearch(@RequestParam String query) {
        System.out.println(query);
        return "redirect:/search/" + query;
    }

    /**
     * 跳转到查找结果页面
     *
     * @param pageable
     * @param query
     * @param model
     * @return
     */
    @GetMapping("/search/{query}")
    public String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable String query,
                         Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "show/search";
    }

}
