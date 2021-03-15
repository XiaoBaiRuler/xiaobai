package com.xiaobairuler.xiaobai.service;

import com.xiaobairuler.xiaobai.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @Author 小白
 * @create 2020/4/16 14:54
 */
public interface TypeService {

    /**
     * 通过type保存
     *
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 通过id获取type
     *
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 通过名字获取type
     *
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 通过pageable获取Page<Type>
     *
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 获取type列表
     *
     * @return
     */
    List<Type> listType();

    /**
     * 通过id，和type更新数据
     *
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id, Type type);

    /**
     * 通过id删除数据
     *
     * @param id
     */
    void deleteType(Long id);
}
