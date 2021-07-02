package com.wblog.notice.service;

import java.util.List ;
import java.util.Collection ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams ;
import com.wblog.common.module.notice.vo.MNoticeTemplateVo ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo ;
/**
 *
 * 通知消息之消息模板 service
 *
 *
 * @author luyanan
 * @since 2021-06-29
*/
public interface IMNoticeTemplateService  {

/**
* 分页查询
*
* @param pageRequestParams 分页参数
* @return PageInfo<MNoticeTemplateVo>
 * @since 2021-06-29
 */
 PageInfo<MNoticeTemplateVo> listByPage(PageRequestParams<MNoticeTemplateVo> pageRequestParams);


   /**
   * 根据条件查询
   *
   * @param mNoticeTemplateVo 根据条件查询
   * @return java.util.List<MNoticeTemplateVo>
    * @since 2021-06-29
    */
    List<MNoticeTemplateVo> list(MNoticeTemplateVo mNoticeTemplateVo);

     /**
     * 根据id查询
     *
     * @param id id
     * @return MNoticeTemplateVo
     * @since 2021-06-29
     */
     MNoticeTemplateVo findById(Long id);

     /**
     * 添加
     *
     * @param mNoticeTemplateVo 实体
     * @return void
     * @since 2021-06-29
     */
     void insert(MNoticeTemplateVo mNoticeTemplateVo);


     /**
     * 根据id修改
     *
     * @param mNoticeTemplateVo 需要修改的对象
     * @return void
     * @since 2021-06-29
     */
     void update(MNoticeTemplateVo mNoticeTemplateVo);


     /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return void
     * @since 2021-06-29
     */
     void deleteByIds(Collection<Long> ids);

}