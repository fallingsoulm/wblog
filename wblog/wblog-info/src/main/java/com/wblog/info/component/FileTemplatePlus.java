package com.wblog.info.component;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.wblog.common.enums.FilePathEnum;
import com.wblog.info.config.BlogConfigProperties;
import io.github.fallingsoulm.easy.archetype.data.file.FileProperties;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.file.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文件上传辅助类
 *
 * @author luyanan
 * @since 2021/5/30
 **/
public class FileTemplatePlus extends FileTemplate {


    @Autowired
    private BlogConfigProperties blogConfigProperties;

    public FileTemplatePlus(IFileService fileService, FileProperties fileProperties) {
        super(fileService, fileProperties);
    }


    /**
     * 随机图片
     *
     * @return java.lang.String
     * @since 2021/5/7
     */
    public String randomImage() {
        Assert.notBlank(blogConfigProperties.getRandomImagePath(), "随机图片的地址不能为空");
        List<String> paths =
                super.loopFiles(FilePathEnum.PUBLIC.getPath());
        if (CollectionUtil.isNotEmpty(paths)) {
            return paths.get(RandomUtil.randomInt(paths.size()));
        }
        return null;
    }

}
