package com.wblog.notice.service;

import java.util.List;
import java.util.Collection;

import com.wblog.common.module.notice.vo.MNoticeMessageVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;

/**
 * 通知消息详情 service
 *
 * @author luyanan
 * @since 2021-06-29
 */
public interface IMNoticeMessageService {

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页参数
     * @return PageInfo<MNoticeMessageVo>
     * @since 2021-06-29
     */
    PageInfo<MNoticeMessageVo> listByPage(PageRequestParams<MNoticeMessageVo> pageRequestParams);


    /**
     * 根据条件查询
     *
     * @param mNoticeMessageVo 根据条件查询
     * @return java.util.List<MNoticeMessageVo>
     * @since 2021-06-29
     */
    List<MNoticeMessageVo> list(MNoticeMessageVo mNoticeMessageVo);

    /**
     * 根据id查询
     *
     * @param id id
     * @return MNoticeMessageVo
     * @since 2021-06-29
     */
    MNoticeMessageVo findById(Long id);

    /**
     * 添加
     *
     * @param mNoticeMessageVo 实体
     * @return void
     * @since 2021-06-29
     */
    void insert(MNoticeMessageVo mNoticeMessageVo);


    /**
     * 根据id修改
     *
     * @param mNoticeMessageVo 需要修改的对象
     * @return void
     * @since 2021-06-29
     */
    void update(MNoticeMessageVo mNoticeMessageVo);


    /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return void
     * @since 2021-06-29
     */
    void deleteByIds(Collection<Long> ids);

    /**
     * 消息发送
     *
     * @param token    token
     * @param title    标题
     * @param content  内容
     * @param template 模板类型
     * @return void
     * @since 2021/6/30
     */
    void sendMessage(String token, String title, String content, String template);
}