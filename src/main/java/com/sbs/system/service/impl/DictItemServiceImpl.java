package com.sbs.system.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sbs.common.entity.QueryRequest;
import com.sbs.system.entity.DictItem;
import com.sbs.system.mapper.DictItemMapper;
import com.sbs.system.service.IDictItemService;

/**
 *  Service实现
 *
 * @author test
 * @date 2020-02-13 14:34:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictItemService {

    @Override
    public IPage<DictItem> findDictItems(QueryRequest request, DictItem dictItem) {
    	
        Page<DictItem> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper(dictItem));
    }

    private QueryWrapper<DictItem> queryWrapper(DictItem dictItem) {
    	QueryWrapper<DictItem> queryWrapper = new QueryWrapper<>();
	    if (StringUtils.isNotBlank(dictItem.getCode())) {
	    	queryWrapper.lambda().eq(DictItem::getCode, dictItem.getCode());
	    }
	    if (StringUtils.isNotBlank(dictItem.getName())) {
	    	queryWrapper.lambda().like(DictItem::getName, dictItem.getName());
	    }
	    queryWrapper.lambda().eq(DictItem::getDictId, dictItem.getDictId());
	    return queryWrapper;
    }
    
    @Override
    public List<DictItem> findDictItems(DictItem dictItem) {
    	
		return this.baseMapper.selectList(queryWrapper(dictItem));
    }
    
    @Override
    @Transactional
    public void createDictItem(DictItem dictItem) {
        this.save(dictItem);
    }

    @Override
    @Transactional
    public void updateDictItem(DictItem dictItem) {
    	this.saveOrUpdate(dictItem);
    }

    @Override
    @Transactional
    public void deleteDictItems(String dictItemIds) {
		List<String> list = Arrays.asList(dictItemIds.split(StringPool.COMMA));
		this.baseMapper.delete(new QueryWrapper<DictItem>().lambda().in(DictItem::getId, list));
	}
    
    @Override
    @Transactional
    public void deleteByDictIds(String dictIds) {
		List<String> list = Arrays.asList(dictIds.split(StringPool.COMMA));
		this.baseMapper.delete(new QueryWrapper<DictItem>().lambda().in(DictItem::getDictId, list));
	}

	@Override
	public DictItem findDictItem(DictItem dictItem) {
		List<DictItem> list = this.baseMapper.selectList(queryWrapper(dictItem));
		if(list != null && list.size() > 0)
	    	return list.get(0);
	    else
	    	return null;
	}
}
