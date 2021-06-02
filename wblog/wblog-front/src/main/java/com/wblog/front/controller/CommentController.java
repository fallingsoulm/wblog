package com.wblog.front.controller;


import com.wblog.common.module.info.api.CommentApi;
import com.wblog.common.module.info.vo.CommentVo;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>评论</p>
 *
 * @author luyanan
 * @since 2020/9/19
 */
@RequestMapping("comments")
@Controller
public class CommentController {

    @Value("${comment.avatar:/images/avatar.png}")
    private String avatar;
    @Autowired
    private CommentApi commentApi;

    @GetMapping("{blogId}")
    public String comments(@PathVariable("blogId") Long blogId, Model model) {
        model.addAttribute("comments", commentApi.findByArticleId(blogId).getData());
        return "blog :: commentList";
    }


    @PostMapping
    public String post(CommentVo commentEntity) {
        Long blogId = commentEntity.getArticle().getId();
        commentEntity.setArticleId(blogId);
        LoginUserVo userEntity = null;
        commentEntity.setAvatar(avatar);
        if (userEntity != null) {
            commentEntity.setAdminComment(Boolean.TRUE);
        }
        commentApi.save(commentEntity);
        return "redirect:/comments/" + blogId;
    }
}
