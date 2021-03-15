package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


/**
 * @Author 小白
 * @create 2020/4/17 3:40
 */
public interface MusicRepository extends JpaRepository<Music, Long> {

    /**
     * 获取音乐列表
     *
     * @return
     */
    @Query(value = "select * from t_music", nativeQuery = true)
    ArrayList<Music> getAllMusics();

    /**
     * 获取音乐的分页列表
     *
     * @param pageable
     * @return
     */
    Page<Music> findAll(Pageable pageable);

    /**
     * 从数据库中通过id查找music
     *
     * @param id
     * @return
     */
    Music findOneById(Long id);

    /**
     * 从数据库中通过url查找music
     *
     * @param url
     * @return
     */
    Music findByUrl(String url);

    /**
     * 通过id删除music
     *
     * @param id
     */
    void deleteById(Long id);
}
