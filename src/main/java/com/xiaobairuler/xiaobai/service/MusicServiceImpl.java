package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.MusicRepository;
import com.xiaobairuler.xiaobai.exception.NotFoundException;
import com.xiaobairuler.xiaobai.po.Music;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author 小白
 * @create 2020/4/17 3:45
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Transactional
    @Override
    public ArrayList<Music> getAllMusics() {
        return musicRepository.getAllMusics();
    }

    @Transactional
    @Override
    public Page<Music> listMusic(Pageable pageable) {
        return musicRepository.findAll(pageable);
    }

    /**
     * 通过Music保存
     *
     * @param music
     * @return
     */
    @Transactional
    @Override
    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    /**
     * 通过id获取Music
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Music getMusic(Long id) {
        return musicRepository.findOneById(id);
    }

    /**
     * 通过url获取Music
     *
     * @param url
     * @return
     */
    @Transactional
    @Override
    public Music getMusicByUrl(String url) {
        return musicRepository.findByUrl(url);
    }


    /**
     * 通过id，和Music更新数据
     *
     * @param id
     * @param music
     * @return
     */
    @Transactional
    @Override
    public Music updateMusic(Long id, Music music) {
        Music t = musicRepository.findOneById(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(music, t);
        return musicRepository.save(t);
    }

    /**
     * 通过id删除数据
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }
}
