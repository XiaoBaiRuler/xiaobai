package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Blog;
import com.xiaobairuler.xiaobai.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Year;
import java.util.List;
import java.util.Map;

/**
 * @Author 小白
 * @create 2020/4/19 13:25
 */
public interface BlogService {

    /**
     * 通过id获取博客
     *
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 获取内容并转换格式
     *
     * @param id
     * @return
     */
    Blog getAndCovert(Long id);

    /**
     * 通过Blog获取页面列表
     *
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 获取页面列表
     *
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 获取页面列表
     *
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query, Pageable pageable);

    /**
     * 获取推荐的列表
     *
     * @return
     */
    List<Blog> listBlogRecommend();

    /**
     * 按时间排序获取所有年份
     */
    Map<String, List<Blog>> timeBlogs();

    /**
     * 通过Blog保存
     *
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 通过Blog更新
     *
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteBlog(Long id);


}
