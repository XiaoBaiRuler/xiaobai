package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Image;

import java.util.List;
import java.util.Optional;

/**
 * @Author 小白
 * @create 2020/4/25 1:23
 */
public interface ImageService {

    List<Image> listImages();

    Image saveImage(Image image);

    Optional<Image> findImage(Long id);

    void deleteImage(Long id);
}
