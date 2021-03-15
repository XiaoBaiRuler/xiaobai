package com.xiaobairuler.xiaobai.controller.admin;

import com.xiaobairuler.xiaobai.po.Tag;
import com.xiaobairuler.xiaobai.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author 小白
 * @create 2020/4/16 15:15
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 分页获取列表操作
     * 1. 列表倒叙
     * 2. 列表展示10行
     * 3. 跳转到Tag_admin页面
     *
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String list(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tag_admin";
    }

    /**
     * 添加页面跳转
     * 1. 页面需要返回一个空Tag的attribute,为了做后端的非空检测
     *
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag_input";
    }

    /**
     * 编辑页面跳转
     * 1. 页面需要返回一个空Tag的attribute,为了做后端的非空检测
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag_input";
    }

    /**
     * 添加页面控制
     * 1，重复检测，返回错误信息
     * 2. 非空检测，回到添加页面
     * 3. 添加成功判断，返回信息
     * 4. 重定向回列表页面
     *
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag Tag2 = tagService.getTagByName(tag.getName());
        if (Tag2 != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if (result.hasErrors()) {
            return "admin/tag_input";
        }
        Tag Tag1 = tagService.saveTag(tag);
        if (Tag1 == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 编辑页面控制
     * 1，重复检测，返回错误信息(注意：不区分大小写，以当前的名字不变修改，当成重复)
     * 2. 非空检测，回到添加页面
     * 3. 添加成功判断，返回信息
     * 4. 重定向回列表页面
     *
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag Tag2 = tagService.getTagByName(tag.getName());
        if (Tag2 != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if (result.hasErrors()) {
            return "admin/tag_input";
        }
        Tag Tag1 = tagService.updateTag(id, tag);
        if (Tag1 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除页面控制
     * 1. 删除，并将成功信息返回
     *
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
