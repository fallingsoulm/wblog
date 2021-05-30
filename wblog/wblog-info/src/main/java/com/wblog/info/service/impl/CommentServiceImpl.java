package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;

import com.wblog.common.module.info.vo.CommentVo;
import com.wblog.info.entity.CommentEntity;
import com.wblog.info.manage.ICommentManage;
import com.wblog.info.service.ICommentService;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p></p>
 **/
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentManage commentManage;


    @Override

    public List<CommentVo> findByArticleId(Long articleId) {
        List<CommentEntity> list = commentManage.findByBlogIdAndParentCommentNull(articleId);
        List<CommentVo> commentVos = BeanUtils.copyList(list, CommentVo.class);
//        getReply(commentVos);
        eachComment(commentVos);
        return commentVos;
    }

    private void eachComment(List<CommentVo> commentVos) {
        for (CommentVo commentVo : commentVos) {
            //查询子节点
            List<CommentEntity> list = commentManage.list(CommentEntity.builder().parentCommentId(commentVo.getId()).build());
            List<CommentVo> vos = BeanUtils.copyList(list, CommentVo.class);
            for (CommentVo vo : vos) {
                vo.setParentCommentId(vo.getId());
                vo.setParentCommentNickName(vo.getNickName());
            }
            commentVo.setReplyComments(vos);
            if (CollectionUtil.isNotEmpty(vos)) {
                eachComment(vos);
            }
        }
    }
//
//    private void getReply(List<CommentVo> commentEntities) {
//        if (CollectionUtil.isNotEmpty(commentEntities)) {
//            for (CommentVo commentEntity : commentEntities) {
//                each(commentEntity);
//            }
//        }
//    }
//
//    private void each(CommentVo commentEntity) {
//        List<CommentEntity> commentEntities = commentManage.
//                findList(CommentEntity.builder().parentCommentId(commentEntity.getId()).build());
//        List<CommentVo> list = commentConver.mapAsList(commentEntities, CommentVo.class);
//        if (CollectionUtil.isNotEmpty(list)) {
//            list.forEach(a -> {
//                a.setParentComment(commentEntity);
//                each(a);
//            });
//        }
//        commentEntity.setReplyComments(list);
//    }
//
//    /**
//     * 循环每个顶级的评论节点
//     *
//     * @param comments
//     * @return
//     */
//    private List<CommentVo> eachComment(List<CommentVo> comments) {
//        List<CommentVo> commentsView = new ArrayList<>();
//        for (CommentVo comment : comments) {
//            CommentVo c = new CommentVo();
//            BeanUtils.copyProperties(comment, c);
//            commentsView.add(c);
//        }
//        //合并评论的各层子代到第一级子代集合中
//        combineChildren(commentsView);
//        return commentsView;
//    }
//
//    /**
//     * @param comments root根节点，blog不为空的对象集合
//     * @return
//     */
//    private void combineChildren(List<CommentVo> comments) {
//
//        for (CommentVo comment : comments) {
//            List<CommentVo> replys1 = comment.getReplyComments();
//            for (CommentVo reply1 : replys1) {
//                //循环迭代，找出子代，存放在tempReplys中
//                recursively(reply1);
//            }
//            //修改顶级节点的reply集合为迭代处理后的集合
//            comment.setReplyComments(tempReplys);
//            //清除临时存放区
//            tempReplys = new ArrayList<>();
//        }
//    }
//
//    //存放迭代找出的所有子代的集合
//    private List<CommentVo> tempReplys = new ArrayList<>();
//
//    /**
//     * 递归迭代，剥洋葱
//     *
//     * @param comment 被迭代的对象
//     * @return
//     */
//    private void recursively(CommentVo comment) {
//        tempReplys.add(comment);//顶节点添加到临时存放集合
//        if (comment.getReplyComments().size() > 0) {
//            List<CommentVo> replys = comment.getReplyComments();
//            for (CommentVo reply : replys) {
//                tempReplys.add(reply);
//                if (reply.getReplyComments().size() > 0) {
//                    recursively(reply);
//                }
//            }
//        }
//    }


    @Override
    public void save(CommentVo commentVo) {
        CommentEntity commentEntity = BeanUtils.copyProperties(commentVo, CommentEntity.class);
        Long parentCommentId = commentVo.getParentComment().getId();
        if (parentCommentId.intValue() == -1) {
            commentEntity.setParentCommentId(null);
        } else {
            commentEntity.setParentCommentId(parentCommentId);
        }
        commentEntity.setCreateTime(System.currentTimeMillis());
        commentManage.insert(commentEntity);
    }
}
