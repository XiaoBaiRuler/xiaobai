package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.dao.TypeRepository;
import com.xiaobairuler.xiaobai.exception.NotFoundException;
import com.xiaobairuler.xiaobai.po.Type;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * 通过type保存
     *
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 通过id获取type
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findOneById(id);
    }

    /**
     * 通过名字获取type
     *
     * @param name
     * @return
     */
    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    /**
     * 通过pageable获取Page<Type>
     *
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    /**
     * 通过id，和type更新数据
     *
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOneById(id);
        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    /**
     * 通过id删除数据
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
