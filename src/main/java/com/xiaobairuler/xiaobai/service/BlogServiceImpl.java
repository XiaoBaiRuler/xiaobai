package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.BlogRepository;
import com.xiaobairuler.xiaobai.exception.NotFoundException;
import com.xiaobairuler.xiaobai.po.Blog;
import com.xiaobairuler.xiaobai.po.Tag;
import com.xiaobairuler.xiaobai.po.Type;
import com.xiaobairuler.xiaobai.util.MarkdownUtils;
import com.xiaobairuler.xiaobai.util.MyBeanUtils;
import com.xiaobairuler.xiaobai.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @Author 小白
 * @create 2020/4/19 13:30
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOneById(id);
    }

    @Transactional
    @Override
    public Blog getAndCovert(Long id) {
        Blog blog = blogRepository.findOneById(id);
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return b;
    }


    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + blog.getTitle() + "%"));
            }
            if (blog.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }
            if (blog.getTagId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Tag>get("tag").get("id"), blog.getTagId()));
            }
            if (blog.isRecommend()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

            return null;
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAllByPublished(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Override
    public List<Blog> listBlogRecommend() {
        return blogRepository.findTop();
    }

    @Override
    public Map<String, List<Blog>> timeBlogs() {
        List<String> years = blogRepository.findAllYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog blog1 = blogRepository.findOneById(id);
        if (blog1 == null) {
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog, blog1, MyBeanUtils.getNullPropertyNames(blog));
        blog1.setUpdateTime(new Date());
        return blogRepository.save(blog1);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
