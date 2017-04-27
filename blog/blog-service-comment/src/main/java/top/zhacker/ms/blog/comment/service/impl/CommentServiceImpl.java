package top.zhacker.ms.blog.comment.service.impl;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.zhacker.ms.blog.comment.common.CommentServiceException;
import top.zhacker.ms.blog.comment.common.Paging;
import top.zhacker.ms.blog.comment.dto.CommentPagingCriteria;
import top.zhacker.ms.blog.comment.model.Comment;
import top.zhacker.ms.blog.comment.repo.CommentRepo;
import top.zhacker.ms.blog.comment.service.CommentService;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public String createComment(Comment comment) {
        try {
            commentRepo.save(comment);
            return comment.getId();
        }catch (Exception e){
            log.error("createComment fail, comment={}, cause={}",
                    comment, Throwables.getStackTraceAsString(e));
            throw new CommentServiceException("comment.post.fail");
        }
    }

    @Override
    public Boolean deleteCommentById(String commentId, String operatorId) {
        try{
            Comment entity = commentRepo.findOne(commentId);
            if(entity==null){
                return Boolean.FALSE;
            }
            if(!entity.getAuthorId().equals(operatorId)){
                log.warn("permission.deny when deleteCommentById, comment={}, operatorId={}", entity, operatorId);
                throw new CommentServiceException("permission.deny");
            }
            commentRepo.delete(commentId);

            return Boolean.TRUE;
        }catch (Exception e){
            Throwables.propagateIfInstanceOf(e, CommentServiceException.class);

            log.error("deleteCommentById fail, commentId={}, operatorId={}, cause={}",
                    commentId, operatorId, Throwables.getStackTraceAsString(e));
            throw new CommentServiceException("delete.comment.fail");
        }
    }

    @Override
    public Paging<Comment> findCommentsByBlogId(CommentPagingCriteria criteria) {
        try{
            Page<Comment> comments;

            //根据作者ID进行分页查询
            Pageable pageable = new PageRequest(criteria.getPageNo(), criteria.getPageSize(), Sort.Direction.ASC, "id");
            if(criteria.getPostId()!=null) {
                comments = commentRepo.findByPostId(criteria.getPostId(), pageable);
            }else{
                comments = commentRepo.findAll(pageable);
            }

            return new Paging<>(comments.getTotalElements(), comments.getContent());
        }catch (Exception e){
            log.error("findCommentsByBlogId fail, criteria={}, cause={}", criteria, Throwables.getStackTraceAsString(e));
            throw new CommentServiceException("find.comment.by.blog.id.fail");
        }
    }
}
