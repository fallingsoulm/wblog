package com.wblog.info.mq.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/11/21
 * <p>修改文章的标签</p>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateArticleLabelTo  implements Serializable {

    /**
     * 文章id
     *
     * @author luyanan
     * @since 2020/11/21
     */
    private List<Long> articleIds;

    /**
     * 标签id
     *
     * @author luyanan
     * @since 2020/11/21
     */
    private List<Long> labelIds;

}
