package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 小白
 * @create 2020/4/15 23:51
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名和密码查找数据库中的Type表
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
