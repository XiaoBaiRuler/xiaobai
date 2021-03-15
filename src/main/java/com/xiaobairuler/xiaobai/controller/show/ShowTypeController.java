package com.xiaobairuler.xiaobai.controller.show;

import com.xiaobairuler.xiaobai.po.Type;
import com.xiaobairuler.xiaobai.service.BlogService;
import com.xiaobairuler.xiaobai.service.TypeService;
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
 * @create 2020/4/22 21:13
 */
@Controller
public class ShowTypeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    /**
     * 返回分类列表和按分类返回博客分页列表
     *
     * @param pageable
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/type/{id}")
    public String toType(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model,
                         @PathVariable Long id) {
        List<Type> types = typeService.listType();

        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "show/classific";
    }
}
