package com.wblog.info.api;

import com.apes.hub.api.module.info.vo.CommentVo;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.info.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论的接口</p>
 **/
@Api(description = "评论")
@RequestMapping(Version.VERSION_1 + "comment")
@RestController
public class CommentApiController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "根据文章id查询评论")
    @GetMapping("find/articleId/{articleId}")
    public RespEntity<List<CommentVo>> findByArticleId(@PathVariable("articleId") Long articleId) {
        List<CommentVo> commentVos = commentService.findByArticleId(articleId);
        return RespEntity.success(commentVos);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("save")
    public RespEntity save(@RequestBody CommentVo commentVo) {

        commentService.save(commentVo);
        return RespEntity.success();
    }
}
