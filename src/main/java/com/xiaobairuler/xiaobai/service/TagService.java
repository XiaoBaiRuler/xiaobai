package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @Author 小白
 * @create 2020/4/16 14:54
 */
public interface TagService {

    /**
     * 通过tag保存
     *
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 通过id获取tag
     *
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 通过名字获取Tag
     *
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 通过pageable获取Page<Tag>
     *
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 获取tag列表
     *
     * @return
     */
    List<Tag> listTag();

    /**
     * 通过id，和Tag更新数据
     *
     * @param id
     * @param Tag
     * @return
     */
    Tag updateTag(Long id, Tag Tag);

    /**
     * 通过id删除数据
     *
     * @param id
     */
    void deleteTag(Long id);
}
