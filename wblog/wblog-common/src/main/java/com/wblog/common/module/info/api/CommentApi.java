package com.wblog.common.module.info.api;

import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.CommentVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("apes-hub-business")
public interface CommentApi {
    String prefix = "business/" + Version.VERSION_1 + "comment/";

    @ApiOperation(value = "根据文章id查询评论")
    @GetMapping(prefix + "find/articleId/{articleId}")
    RespEntity<List<CommentVo>> findByArticleId(@PathVariable("articleId") Long articleId);

    @ApiOperation(value = "添加评论")
    @PostMapping(prefix + "save")
    RespEntity save(@RequestBody CommentVo commentVo);
}
