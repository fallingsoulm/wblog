package com.wblog.system.service.impl;

import com.wblog.system.entity.SysDictDataDo;
import com.wblog.system.manage.ISysDictDataManage;
import com.wblog.system.service.ISysDictDataService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 字典数据表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {

	@Autowired
	private ISysDictDataManage iSysDictDataManage;

	@Override
	public List<SysDictDataDo> list(SysDictDataDo sysDictDataDo) {
		return Optional.ofNullable(iSysDictDataManage.list(sysDictDataDo)).orElse(new ArrayList<>());
	}

	@Override
	public void updateDictType(String newDictType, String oldDictType) {
		iSysDictDataManage.update(SysDictDataDo.builder().dictType(newDictType).build(),
				SysDictDataDo.builder().dictType(oldDictType).build());
	}

	@Override
	public Integer count(SysDictDataDo sysDictDataDo) {


		return iSysDictDataManage.count(sysDictDataDo);

	}

	@Override
	public PageInfo<SysDictDataDo> findByPage(PageRequestParams<SysDictDataDo> pageRequestParams) {
		return iSysDictDataManage.listByPage(pageRequestParams);
	}

	@Override
	public void insert(SysDictDataDo dict) {
		this.iSysDictDataManage.insert(dict);
	}

	@Override
	public void update(SysDictDataDo dict) {

		iSysDictDataManage.update(dict);
	}

	@Override
	public void deleteById(Long[] dictCodes) {
		iSysDictDataManage.deleteBatch(Arrays.asList(dictCodes));
	}
}
