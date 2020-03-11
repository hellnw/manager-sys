package com.sbs.system.service;

import com.sbs.system.entity.Dict;

import com.sbs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author test
 * @date 2020-02-13 14:34:53
 */
public interface IDictService extends IService<Dict> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param item item
     * @return IPage<Item>
     */
    IPage<Dict> findDicts(QueryRequest request, Dict dict);

    /**
     * 查询（所有）
     *
     * @param item item
     * @return List<Item>
     */
    List<Dict> findDicts(Dict dict);

    /**
     * 新增
     *
     * @param item item
     */
    void createDict(Dict dict);

    /**
     * 修改
     *
     * @param item item
     */
    void updateDict(Dict dict);

    /**
     * 删除
     *
     * @param item item
     */
    void deleteDicts(String dictIds);
    
    Dict findDict(Dict dict);
    
    /**
     * 根据主表子表code，获取对应的name
     * @param dict
     * @return
     */
    String findDictItemName(String code, String itemCode);
}
