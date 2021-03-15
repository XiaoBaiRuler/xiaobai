package com.xiaobairuler.xiaobai.dao;

import com.xiaobairuler.xiaobai.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/19 13:31
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    /**
     * 通过id从数据库中找博客
     *
     * @param id
     * @return
     */
    Blog findOneById(Long id);

    /**
     * 获取推荐的博客，现在不打算实现，等2.0添加轮播实现
     *
     * @return
     */
    @Query(value = "select b from Blog b where b.recommend = true")
    List<Blog> findTop();

    /**
     * 通过id保存
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 通过查找的条件，在标题和内容上查找博客分页列表
     *
     * @param query
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    /**
     * 获取博客的分页列表
     *
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.published = 1 ")
    Page<Blog> findAllByPublished(Pageable pageable);

    /**
     * 给博客的浏览次数加一
     *
     * @param id
     * @return
     */
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    /**
     * 获取博客的年份列表
     *
     * @return
     */
    @Query("select function('date_format',b.createTime,'%Y') as year from Blog b group by function('date_format',b" +
            ".createTime,'%Y') order by year asc")
    List<String> findAllYear();

    /**
     * 通过年份获取博客列表
     *
     * @param year
     * @return
     */
    @Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
