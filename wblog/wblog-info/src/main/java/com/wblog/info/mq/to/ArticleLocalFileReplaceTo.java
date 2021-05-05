package com.wblog.info.mq.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author luyanan
 * @since 2020/11/21
 * <p>文件详情图片替换</p>
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleLocalFileReplaceTo  implements Serializable {


    /**
     * 文章详情id
     *
     * @author luyanan
     * @since 2020/11/21
     */
    private Long articleId;
}
