package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author 小白
 * @create 2020/4/16 14:58
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    /**
     * 从数据库里查找列表(分页的)
     *
     * @param pageable
     * @return
     */
    Page<Type> findAll(Pageable pageable);

    /**
     * 从数据库中通过id查找Type
     *
     * @param id
     * @return
     */
    Type findOneById(Long id);

    /**
     * 从数据库中通过name查找Type
     *
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 通过id删除Type
     *
     * @param id
     */
    void deleteById(Long id);
}
