package com.wblog.info.thread;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.wblog.common.module.info.vo.ArticleInfoVo;
import com.wblog.common.module.info.vo.GitSynVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.config.BlogConfigProperties;
import com.wblog.info.service.IArticleInfoService;
import com.wblog.info.service.IGitSynService;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luyanan
 * @since 2020/7/10
 * <p>文章本地图片替换处理</p>
 **/
@Slf4j
public class ArticleLocalFileReplaceThread extends Thread {


    private Long articleId;

    private IGitSynService gitSynService;

    private BlogConfigProperties blogConfigProperties;

    private FileTemplatePlus fileTemplatePlus;

    private IArticleInfoService articleInfoService;

    public ArticleLocalFileReplaceThread(Long articleId
            , IGitSynService gitSynService
            , BlogConfigProperties blogConfigProperties
            , FileTemplatePlus fileTemplatePlus
            , IArticleInfoService articleInfoService) {
        this.articleId = articleId;
        this.gitSynService = gitSynService;
        this.blogConfigProperties = blogConfigProperties;
        this.fileTemplatePlus = fileTemplatePlus;
        this.articleInfoService = articleInfoService;
    }

    private Pattern imagePattern = Pattern.compile("!\\[.+?\\]\\((.+?)\\)");


    @Override
    public void run() {
        ArticleInfoVo articleInfoVo = articleInfoService.findById(articleId);
        if (null == articleInfoVo || StrUtil.isBlank(articleInfoVo.getContent())) {
            return;
        }
        String content = articleInfoVo.getContent();

        // 查询文章路径
        GitSynVo gitSynVo = gitSynService.findOne(GitSynVo.builder().articleId(articleId).build());
        if (null == gitSynVo) {
            return;
        }
        String path = Paths.get(gitSynVo.getPath()).getParent().toString();
        Matcher matcher = imagePattern.matcher(content);
        boolean isUpdate = false;
        while (matcher.find()) {
            isUpdate = true;
            String group = matcher.group();
            String imagePath = ReUtil.get("\\((.+?)\\)", group, 0);

            imagePath = imagePath.trim().substring(1, imagePath.length() - 1);
            // 外链无需替换
            if (imagePath.startsWith("http")) {
                return;
            }


            String absolutePath = blogConfigProperties.getGitSynPath() + path + "/" + imagePath;


            absolutePath = absolutePath.replace("\\", "/");
            absolutePath = URLUtil.decode(absolutePath);
            // 判断文件是否存在
            if (!FileUtil.exist(absolutePath)) {
                continue;
            }
            byte[] bytes = FileUtil.readBytes(absolutePath);
//            String upload = fileApi.upload(
//                    bytes, absolutePath, FilePathEnum.BLOG_INFO.getClassify(), true
//            );
//            if (StrUtil.isBlank(upload)) {
//                log.error("{}图片上传失败,文章id:{}", absolutePath, articleId);
//                return;
//
//            }
//            content = content.replace(imagePath, upload);
        }
        if (isUpdate) {
            // 文章修改
            articleInfoService.update(ArticleInfoVo.builder().articleId(articleId).content(content).build());

        }
    }

}
