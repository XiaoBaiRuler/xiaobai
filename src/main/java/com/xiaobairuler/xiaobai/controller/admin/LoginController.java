package com.xiaobairuler.xiaobai.controller.admin;

import com.xiaobairuler.xiaobai.po.User;
import com.xiaobairuler.xiaobai.service.UserService;
import com.xiaobairuler.xiaobai.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author 小白
 * @create 2020/4/15 23:59
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 1. 先检查用户是否存在(密码使用MD5加密后的)
     * 2. 成功，跳转到博客管理页面
     *
     * @param username
     * @param password
     * @param session    将用户信息返回页面(密码置空)
     * @param attributes 将错误信息返回，redirect到/admin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/main";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销，清除掉session里的user信息，redirect到/admin
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
