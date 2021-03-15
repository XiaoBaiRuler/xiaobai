package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 小白
 * @create 2020/4/25 1:27
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
