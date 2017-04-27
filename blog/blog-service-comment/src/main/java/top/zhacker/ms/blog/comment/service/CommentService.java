package top.zhacker.ms.blog.comment.service;


import top.zhacker.ms.blog.comment.common.Paging;
import top.zhacker.ms.blog.comment.dto.CommentPagingCriteria;
import top.zhacker.ms.blog.comment.model.Comment;

/**
 * DATE: 17/3/24 下午1:41 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 评论服务
 */

public interface CommentService {

    String createComment(Comment comment);

    Boolean deleteCommentById(String commentId, String operatorId);

    Paging<Comment> findCommentsByBlogId(CommentPagingCriteria commentPagingCriteria);
}
