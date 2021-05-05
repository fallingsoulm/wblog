package com.wblog.info.service.impl;

import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.exception.CustomException;
import com.apes.hub.api.module.info.vo.ChainCollectionClassifyVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.ChainCollectionClassifyConver;
import com.apes.hub.info.entity.ChainCollectionClassifyEntity;
import com.apes.hub.info.manage.IChainCollectionClassifyManage;
import com.apes.hub.info.service.IChainCollectionClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 链藏分类表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Service
@Slf4j
public class ChainCollectionClassifyServiceImpl implements IChainCollectionClassifyService {


    @Autowired
    private IChainCollectionClassifyManage iChainCollectionClassifyManage;


    @Autowired
    private ChainCollectionClassifyConver chainCollectionClassifyConver;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<ChainCollectionClassifyVo> findByPage(PageRequestParams<ChainCollectionClassifyVo> pageRequestParams) {
        PageRequestParams<ChainCollectionClassifyEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ChainCollectionClassifyEntity.class, chainCollectionClassifyConver);
        PageInfo<ChainCollectionClassifyEntity> entityPageInfo = iChainCollectionClassifyManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ChainCollectionClassifyVo.class, chainCollectionClassifyConver);
    }

    @Override
    public ChainCollectionClassifyVo findById(Long id) {
        if (id.equals(0L)) {
            return ChainCollectionClassifyVo.builder().id(0L)
                    .parentId(0L)
                    .name("无")
                    .type(ConstantEnum.CHAIN_COLLECTION_TYPE_SYSTEM.getValue()).build();
        }
        ChainCollectionClassifyEntity chainCollectionClassifyEntity = iChainCollectionClassifyManage.findById(id);
        if (chainCollectionClassifyEntity == null) {
            return null;
        }
        ChainCollectionClassifyVo classifyVo = chainCollectionClassifyConver.map(chainCollectionClassifyEntity, ChainCollectionClassifyVo.class);
        if (classifyVo.getParentId().equals(0L)) {
            classifyVo.setParentName("无");
        } else {
            classifyVo.setParentName(this.iChainCollectionClassifyManage.findById(classifyVo.getParentId()).getName());
        }
        return classifyVo;
    }

    @Override
    public List<ChainCollectionClassifyVo> findList(ChainCollectionClassifyVo chainCollectionClassifyVo) {
        ChainCollectionClassifyEntity ChainCollectionClassifyEntity = chainCollectionClassifyConver.map(chainCollectionClassifyVo, ChainCollectionClassifyEntity.class);
        List<ChainCollectionClassifyEntity> list = iChainCollectionClassifyManage.findList(ChainCollectionClassifyEntity);
        return chainCollectionClassifyConver.mapAsList(list, ChainCollectionClassifyVo.class);
    }

    @Override
    public List<ChainCollectionClassifyVo> findByIds(List<Long> ids) {
        List<ChainCollectionClassifyEntity> entities = iChainCollectionClassifyManage.findByIds(ids);
        return chainCollectionClassifyConver.mapAsList(entities, ChainCollectionClassifyVo.class);
    }

    @Override
    public Long save(ChainCollectionClassifyVo chainCollectionClassifyVo) {
        checkUnique(chainCollectionClassifyVo);
        ChainCollectionClassifyEntity chainCollectionClassifyEntity = chainCollectionClassifyConver.map(chainCollectionClassifyVo, ChainCollectionClassifyEntity.class);
        iChainCollectionClassifyManage.insert(chainCollectionClassifyEntity);
        return chainCollectionClassifyEntity.getId();
    }

    /**
     * <p>检查唯一</p>
     *
     * @param chainCollectionClassifyVo
     * @author luyanan
     * @since 2020/6/24
     */
    private void checkUnique(ChainCollectionClassifyVo chainCollectionClassifyVo) {
        ChainCollectionClassifyEntity one = this.iChainCollectionClassifyManage.findOne(ChainCollectionClassifyEntity.builder()
                .name(chainCollectionClassifyVo.getName())
                .parentId(chainCollectionClassifyVo.getParentId())
                .type(chainCollectionClassifyVo.getType()).build());
        if (one != null) {
            throw new CustomException("名字不允许重复");
        }
    }

    @Override
    public void update(ChainCollectionClassifyVo chainCollectionClassifyVo) {
        ChainCollectionClassifyEntity manage = this.iChainCollectionClassifyManage.findById(chainCollectionClassifyVo.getId());
        if (!chainCollectionClassifyVo.getName().equals(manage.getName())) {
            checkUnique(chainCollectionClassifyVo);
        }
        ChainCollectionClassifyEntity chainCollectionClassifyEntity = chainCollectionClassifyConver.map(chainCollectionClassifyVo, ChainCollectionClassifyEntity.class);
        iChainCollectionClassifyManage.update(chainCollectionClassifyEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            List<ChainCollectionClassifyEntity> list = this.iChainCollectionClassifyManage.findList(ChainCollectionClassifyEntity.builder().parentId(id).build());
            if (list != null && list.size() > 0) {
                throw new CustomException("该分类下存在子分类,不允许删除");
            }
        }
        iChainCollectionClassifyManage.deleteBatch(new ChainCollectionClassifyEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iChainCollectionClassifyManage.deleteById(ChainCollectionClassifyEntity.builder().id(id).build());
    }

}
