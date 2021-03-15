package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.TagRepository;
import com.xiaobairuler.xiaobai.exception.NotFoundException;
import com.xiaobairuler.xiaobai.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 小白
 * @create 2020/4/16 14:57
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository TagRepository;

    /**
     * 通过Tag保存
     *
     * @param Tag
     * @return
     */
    @Transactional
    @Override
    public Tag saveTag(Tag Tag) {
        return TagRepository.save(Tag);
    }

    /**
     * 通过id获取Tag
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return TagRepository.findOneById(id);
    }

    /**
     * 通过名字获取Tag
     *
     * @param name
     * @return
     */
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return TagRepository.findByName(name);
    }

    /**
     * 通过pageable获取Page<Tag>
     *
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return TagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return TagRepository.findAll();
    }

    /**
     * 通过id，和Tag更新数据
     *
     * @param id
     * @param Tag
     * @return
     */
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag Tag) {
        Tag t = TagRepository.findOneById(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(Tag, t);
        return TagRepository.save(t);
    }

    /**
     * 通过id删除数据
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteTag(Long id) {
        TagRepository.deleteById(id);
    }
}
