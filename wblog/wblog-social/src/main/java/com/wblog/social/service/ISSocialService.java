package com.wblog.social.service;

import java.util.List ;
import java.util.Collection ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams ;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo ;
import com.wblog.common.module.social.vo.SSocialVo ;
/**
 *
 * 第三方平台表 service
 *
 *
 * @author luyanan
 * @since 2021-07-05
*/
public interface ISSocialService  {

/**
* 分页查询
*
* @param pageRequestParams 分页参数
* @return PageInfo<SSocialVo>
 * @since 2021-07-05
 */
 PageInfo<SSocialVo> listByPage(PageRequestParams<SSocialVo> pageRequestParams);


   /**
   * 根据条件查询
   *
   * @param sSocialVo 根据条件查询
   * @return java.util.List<SSocialVo>
    * @since 2021-07-05
    */
    List<SSocialVo> list(SSocialVo sSocialVo);

     /**
     * 根据id查询
     *
     * @param id id
     * @return SSocialVo
     * @since 2021-07-05
     */
     SSocialVo findById(Long id);

     /**
     * 添加
     *
     * @param sSocialVo 实体
     * @return void
     * @since 2021-07-05
     */
     void insert(SSocialVo sSocialVo);


     /**
     * 根据id修改
     *
     * @param sSocialVo 需要修改的对象
     * @return void
     * @since 2021-07-05
     */
     void update(SSocialVo sSocialVo);


     /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return void
     * @since 2021-07-05
     */
     void deleteByIds(Collection<Long> ids);

}