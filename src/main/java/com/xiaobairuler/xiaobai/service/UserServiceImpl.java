package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.UserRepository;
import com.xiaobairuler.xiaobai.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 小白
 * @create 2020/4/15 23:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 检查用户是否存在的实现
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return user;
        }
        return null;
    }
}
