package com.sbs.system.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sbs.common.entity.QueryRequest;
import com.sbs.system.entity.Dict;
import com.sbs.system.entity.DictItem;
import com.sbs.system.mapper.DictMapper;
import com.sbs.system.service.IDictItemService;
import com.sbs.system.service.IDictService;

/**
 *  Service实现
 *
 * @author test
 * @date 2020-02-13 14:34:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
	@Autowired
    private IDictItemService dictItemService;
	
	
    @Override
    public IPage<Dict> findDicts(QueryRequest request, Dict dict) {
    	
        Page<Dict> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper(dict));
    }

    private QueryWrapper<Dict> queryWrapper(Dict dict) {
    	QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
	    if (StringUtils.isNotBlank(dict.getCode())) {
	    	queryWrapper.lambda().eq(Dict::getCode, dict.getCode());
	    }
	    if (StringUtils.isNotBlank(dict.getName())) {
	    	queryWrapper.lambda().like(Dict::getName, dict.getName());
	    }
	    return queryWrapper;
    }
    
    @Override
    public List<Dict> findDicts(Dict dict) {
    	
		return this.baseMapper.selectList(queryWrapper(dict));
    }
    
    @Override
    public Dict findDict(Dict dict) {
	    List<Dict> list = findDicts(dict);
	    if(list != null && list.size() > 0)
	    	return list.get(0);
	    else
	    	return null;
    }

    @Override
    @Transactional
    public void createDict(Dict dict) {
        this.save(dict);
    }

    @Override
    @Transactional
    public void updateDict(Dict dict) {
        this.saveOrUpdate(dict);
    }

    @Override
    @Transactional
    public void deleteDicts(String dictIds) {
		List<String> list = Arrays.asList(dictIds.split(StringPool.COMMA));
		this.baseMapper.delete(new QueryWrapper<Dict>().lambda().in(Dict::getId, list));
		dictItemService.deleteByDictIds(dictIds);
	}

	@Override
	public String findDictItemName(String code, String itemCode) {
		if(StringUtils.isBlank(code) || StringUtils.isBlank(itemCode))
			return "";
		Dict dict = new Dict();
		dict.setCode(code);
		dict = this.findDict(dict);
		if(dict == null) return "";
		
		DictItem dictItem = new DictItem();
		dictItem.setCode(itemCode);
		dictItem.setDictId(dict.getId());
		dictItem = dictItemService.findDictItem(dictItem);
		if(dictItem == null) return "";
		return StringUtils.isNotBlank(dictItem.getName()) ? dictItem.getName() : "";
	}
}
