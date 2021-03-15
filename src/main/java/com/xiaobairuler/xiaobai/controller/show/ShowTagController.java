package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.po.Tag;
import com.xiaobairuler.xiaobai.service.BlogService;
import com.xiaobairuler.xiaobai.service.TagService;
import com.xiaobairuler.xiaobai.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/22 21:17
 */
@Controller
public class ShowTagController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    /**
     * 返回标签列表和按标签返回博客分页列表
     *
     * @param pageable
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/tag/{id}")
    public String toTag(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model,
                        @PathVariable Long id) {
        List<Tag> tags = tagService.listTag();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTagId(id);
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTagId", id);
        return "show/tags";
    }
}
