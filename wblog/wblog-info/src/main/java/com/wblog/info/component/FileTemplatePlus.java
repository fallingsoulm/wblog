package com.wblog.info.component;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.wblog.common.enums.FilePathEnum;
import com.wblog.common.redis.RedisKeyEnums;
import com.wblog.info.config.BlogConfigProperties;
import io.github.fallingsoulm.easy.archetype.data.file.FileFilterArgs;
import io.github.fallingsoulm.easy.archetype.data.file.FileProperties;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.file.IFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文件上传辅助类
 *
 * @author luyanan
 * @since 2021/5/30
 **/
@Slf4j
public class FileTemplatePlus extends FileTemplate {


    @Autowired
    private RedisTemplate redisTemplate;


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
        String prefix = FilePathEnum.PUBLIC.getPath();
        Assert.notBlank(blogConfigProperties.getRandomImagePath(), "随机图片的地址不能为空");
        Long size = redisTemplate.opsForList().size(RedisKeyEnums.INFO_RANDOM_IMAGE.getKey());
        if (null == size || size.longValue() < 1000) {
            List<String> paths =
                    super.loopFiles(FileFilterArgs.builder().prefix(prefix).size(1000).build());
            log.info("初始化随机头图, 大小为:{}", paths.size());
            for (String path : paths) {
                redisTemplate.opsForList().leftPush(RedisKeyEnums.INFO_RANDOM_IMAGE.getKey(), path);
            }
        }


        Long total = redisTemplate.opsForList().size(RedisKeyEnums.INFO_RANDOM_IMAGE.getKey());

        if (null == total || total.longValue() == 0) {
            return null;
        }
        return (String) redisTemplate.opsForList().index(RedisKeyEnums.INFO_RANDOM_IMAGE.getKey(), RandomUtil.randomInt(total.intValue()));
    }

}
