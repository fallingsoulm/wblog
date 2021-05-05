package com.wblog.common.enums;

import com.wblog.common.exception.BusinessException;

import java.util.Arrays;

/**
 * @author luyanan
 * @since 2020/9/15
 * <p>文件枚举</p>
 **/
public enum FilePathEnum {


    /**
     * <p>公开的</p>
     *
     * @author luyanan
     * @since 2020/6/12
     */
    PUBLIC(100, "public/", "公开"),


    /**
     * <p>博客详情</p>
     *
     * @author luyanan
     * @since 2020/6/12
     */
    BLOG_IMAGE(101, "blog/image/", "文章头图"),


    LABEL_ICON(102, "label/icon/", "标签icon"),

    SYNTHESIZE(200, "synthesize/", "综合"),

    BLOG_INFO(103, "blog/info/", "文章详情图片"),

    ALBUM_IMAGE(104, "ablum/image/", "专辑封面照片"),

    ;
    /**
     * <p>分类</p>
     *
     * @author luyanan
     * @since 2020/6/12
     */
    private Integer classify;


    /**
     * <p>路径</p>
     *
     * @author luyanan
     * @since 2020/6/12
     */
    private String path;


    private String desp;

    public Integer getClassify() {
        return classify;
    }

    public String getPath() {
        return path;
    }

    public String getDesp() {
        return desp;
    }

    FilePathEnum(Integer classify, String path, String desp) {
        this.classify = classify;
        this.path = path;
        this.desp = desp;
    }

    public static FilePathEnum getByClasify(Integer classify) {
        return Arrays
                .stream(FilePathEnum.values())
                .filter(a -> a.getClassify().equals(classify))
                .findFirst()
                .orElseThrow(() -> new BusinessException("未知的分类"));
    }


}
