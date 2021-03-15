package com.xiaobairuler.xiaobai.controller.admin;

import com.xiaobairuler.xiaobai.po.Music;
import com.xiaobairuler.xiaobai.service.MusicService;
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
 * @create 2020/4/17 15:09
 */
@Controller
@RequestMapping("/admin")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 分页获取列表操作
     * 1. 列表倒叙
     * 2. 列表展示10行
     * 3. 跳转到music_admin页面
     *
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/musics")
    public String list(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", musicService.listMusic(pageable));
        return "admin/music_admin";
    }

    /**
     * 到添加页面
     *
     * @return
     */
    @GetMapping("/musics/input")
    public String toinput() {
        return "admin/music_input";
    }

    /**
     * 编辑页面跳转
     * 1. 页面需要返回一个空Music的attribute,为了做后端的非空检测
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/musics/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("music", musicService.getMusic(id));
        return "admin/music_edit";
    }

    /**
     * 编辑页面控制
     * 1，重复检测，返回错误信息(注意：不区分大小写，以当前的名字不变修改，当成重复)
     * 2. 非空检测，回到添加页面
     * 3. 添加成功判断，返回信息
     * 4. 重定向回列表页面
     *
     * @param music
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/musics/{id}")
    public String editPost(@Valid Music music, BindingResult result, @PathVariable Long id,
                           RedirectAttributes attributes) {
        Music Music2 = musicService.getMusicByUrl(music.getUrl());
        if (Music2 != null) {
            result.rejectValue("name", "nameError", "不能修改为重复链接");
        }
        if (result.hasErrors()) {
            return "admin/Music_edit";
        }
        Music Music1 = musicService.updateMusic(id, music);
        if (Music1 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/musics";
    }

    /**
     * 删除页面控制
     * 1. 删除，并将成功信息返回
     *
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/musics/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        musicService.deleteMusic(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/musics";
    }
}
