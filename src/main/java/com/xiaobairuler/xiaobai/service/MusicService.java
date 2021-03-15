package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

/**
 * @Author 小白
 * @create 2020/4/17 3:44
 */
public interface MusicService {

    ArrayList<Music> getAllMusics();

    /**
     * 通过pageable获取Page<Music>
     *
     * @param pageable
     * @return
     */
    Page<Music> listMusic(Pageable pageable);

    /**
     * 通过id，和Music更新数据
     *
     * @param id
     * @param music
     * @return
     */
    Music updateMusic(Long id, Music music);

    /**
     * 通过id删除数据
     *
     * @param id
     */
    void deleteMusic(Long id);

    /**
     * 通过Music保存
     *
     * @param music
     * @return
     */
    Music saveMusic(Music music);

    /**
     * 通过id获取Music
     *
     * @param id
     * @return
     */
    Music getMusic(Long id);

    /**
     * 通过url获取Music
     *
     * @param url
     * @return
     */
    Music getMusicByUrl(String url);
}
