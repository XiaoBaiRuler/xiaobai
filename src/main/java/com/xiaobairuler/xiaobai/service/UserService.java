package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.User;

/**
 * @Author 小白
 * @create 2020/4/15 23:47
 */
public interface UserService {

    /**
     * 检查用户是否存在
     *
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);
}
