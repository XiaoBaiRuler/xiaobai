package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.ImageRepository;
import com.xiaobairuler.xiaobai.po.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author 小白
 * @create 2020/4/25 1:26
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> findImage(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
