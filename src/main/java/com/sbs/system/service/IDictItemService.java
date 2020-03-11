package com.sbs.system.service;

import com.sbs.system.entity.DictItem;
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
public interface IDictItemService extends IService<DictItem> {
    
	IPage<DictItem> findDictItems(QueryRequest request, DictItem dictItem);

    List<DictItem> findDictItems(DictItem dictItem);

    /**
     * 新增
     *
     * @param DictItem dictItem
     */
    void createDictItem(DictItem dictItem);

    /**
     * 修改
     *
     * @param DictItem dictItem
     */
    void updateDictItem(DictItem dictItem);

    /**
     * 删除
     *
     * @param DictItem dictItem
     */
    void deleteDictItems(String dictItemIds);
    
    /**
     * 主表删掉后需删除子表
     * @param dictIds
     */
    void deleteByDictIds(String dictIds);
    
    DictItem findDictItem(DictItem dictItem);
}
