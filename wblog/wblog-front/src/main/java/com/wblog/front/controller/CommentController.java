package com.wblog.front.controller;


import com.apes.hub.api.module.info.api.CommentApi;
import com.apes.hub.api.module.info.vo.CommentVo;
import com.apes.hub.api.module.system.vo.LoginUserVo;
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

    @Value("${comment.avatar}")
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
        if (userEntity != null) {
            commentEntity.setAvatar(avatar);
            commentEntity.setAdminComment(Boolean.TRUE);
        } else {
            commentEntity.setAvatar(avatar);
        }
        commentApi.save(commentEntity);
        return "redirect:/comments/" + blogId;
    }
}
