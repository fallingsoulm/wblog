package com.wblog.info.service.impl;


import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.ChainCollectionClassifyVo;
import com.wblog.common.module.info.vo.ChainCollectionVo;
import com.wblog.info.entity.ChainCollectionEntity;
import com.wblog.info.manage.IChainCollectionManage;
import com.wblog.info.service.IChainCollectionClassifyService;
import com.wblog.info.service.IChainCollectionService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 链藏 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Service
@Slf4j
public class ChainCollectionServiceImpl implements IChainCollectionService {


    @Autowired
    private IChainCollectionManage iChainCollectionManage;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IChainCollectionClassifyService chainCollectionClassifyService;

    @Override
    public PageInfo<ChainCollectionVo> findByPage(PageRequestParams<ChainCollectionVo> pageRequestParams) {
        PageRequestParams<ChainCollectionEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ChainCollectionEntity.class);
        PageInfo<ChainCollectionEntity> entityPageInfo = iChainCollectionManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ChainCollectionVo.class, contentList -> {
            List<Long> classifyIds = contentList.stream().map(ChainCollectionVo::getClassifyId).distinct().collect(Collectors.toList());
            List<ChainCollectionClassifyVo> classifyVos = chainCollectionClassifyService.findByIds(classifyIds);
            for (ChainCollectionVo chainCollectionVo : contentList) {
                for (ChainCollectionClassifyVo classifyVo : classifyVos) {
                    if (chainCollectionVo.getClassifyId().equals(classifyVo.getId())) {
                        chainCollectionVo.setClassifyName(classifyVo.getName());
                    }
                }
            }
        });
    }

    @Override
    public ChainCollectionVo findById(Long id) {
        ChainCollectionEntity chainCollectionEntity = iChainCollectionManage.findById(id);
        if (chainCollectionEntity == null) {
            return null;
        }
        ChainCollectionVo vo = BeanUtils.copyProperties(chainCollectionEntity, ChainCollectionVo.class);
        if (!vo.getClassifyId().equals(0L)) {
            vo.setClassifyName(chainCollectionClassifyService.findById(vo.getClassifyId()).getName());
        }
        return vo;
    }

    @Override
    public List<ChainCollectionVo> findList(ChainCollectionVo chainCollectionVo) {
        ChainCollectionEntity ChainCollectionEntity = BeanUtils.copyProperties(chainCollectionVo, ChainCollectionEntity.class);
        List<ChainCollectionEntity> list = iChainCollectionManage.list(ChainCollectionEntity);
        return BeanUtils.copyList(list, ChainCollectionVo.class);
    }

    @Override
    public List<ChainCollectionVo> findByIds(List<Long> ids) {
        List<ChainCollectionEntity> entities = iChainCollectionManage.findByIds(ids);
        return BeanUtils.copyList(entities, ChainCollectionVo.class);
    }

    @Override
    public Long save(ChainCollectionVo chainCollectionVo) {
        ChainCollectionEntity chainCollectionEntity = BeanUtils.copyProperties(chainCollectionVo, ChainCollectionEntity.class);
        iChainCollectionManage.insert(chainCollectionEntity);
        return chainCollectionEntity.getId();
    }

    @Override
    public void update(ChainCollectionVo chainCollectionVo) {
        ChainCollectionEntity chainCollectionEntity = BeanUtils.copyProperties(chainCollectionVo, ChainCollectionEntity.class);
        iChainCollectionManage.update(chainCollectionEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iChainCollectionManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iChainCollectionManage.deleteById(ChainCollectionEntity.builder().id(id).build());
    }

    @Override
    public Map<String, List<ChainCollectionVo>> listyByMap(Long classifyId) {
        Map<String, List<ChainCollectionVo>> result = new HashMap<>();
        List<ChainCollectionClassifyVo> list = chainCollectionClassifyService.findList(ChainCollectionClassifyVo.builder().parentId(classifyId).build());
        List<Long> classifyIds = list.stream().map(ChainCollectionClassifyVo::getId).distinct().collect(Collectors.toList());
        if (classifyIds.isEmpty()) {
            return result;
        }
        List<ChainCollectionEntity> collectionEntityList = this.iChainCollectionManage.listByIn(classifyIds, ConstantEnum.CHAIN_COLLECTION_STATUS_ENABLE.getValue());
        result = collectionEntityList.stream().map(a -> {
            ChainCollectionVo collectionVo = BeanUtils.copyProperties(a, ChainCollectionVo.class);
            for (ChainCollectionClassifyVo classifyVo : list) {
                if (collectionVo.getClassifyId().equals(classifyVo.getId())) {
                    collectionVo.setClassifyName(classifyVo.getName());
                    break;
                }
            }
            return collectionVo;
        }).collect(Collectors.groupingBy(ChainCollectionVo::getClassifyName));
        return result;
    }

}
