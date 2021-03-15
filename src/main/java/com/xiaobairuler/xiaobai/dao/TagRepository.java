package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 小白
 * @create 2020/4/17 1:35
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * 从数据库里查找列表(分页的)
     *
     * @param pageable
     * @return
     */
    Page<Tag> findAll(Pageable pageable);

    /**
     * 从数据库中通过id查找Type
     *
     * @param id
     * @return
     */
    Tag findOneById(Long id);

    /**
     * 从数据库中通过name查找Type
     *
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 通过id删除Type
     *
     * @param id
     */
    void deleteById(Long id);
}
