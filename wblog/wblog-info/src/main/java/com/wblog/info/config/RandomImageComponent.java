package com.wblog.info.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 随机图片
 *
 * @author luyanan
 * @since 2021/5/7
 **/
@Component
public class RandomImageComponent {

    @Autowired
    private FileTemplate fileTemplate;


    @Autowired
    private BlogConfigProperties blogConfigProperties;

    /**
     * 随机图片
     *
     * @return java.lang.String
     * @since 2021/5/7
     */
    public String randomImage() {
        Assert.notBlank(blogConfigProperties.getRandomImagePath(), "随机图片的地址不能为空");


    }
}
