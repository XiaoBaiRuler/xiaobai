package com.xiaobairuler.xiaobai.controller.show;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 小白
 * @create 2020/4/22 21:22
 */
@Controller
public class ShowAboutController {

    @RequestMapping("/about/xiaobai")
    public String toAboutXiaobai() {
        return "show/about_xiaobai";
    }

    @RequestMapping("/about/web")
    public String toAboutWeb() {
        return "show/about_web";
    }
}
