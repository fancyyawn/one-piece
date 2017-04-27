package top.zhacker.ms.blog.comment.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.zhacker.ms.blog.comment.common.Paging;
import top.zhacker.ms.blog.comment.dto.CommentPagingCriteria;
import top.zhacker.ms.blog.comment.model.Comment;
import top.zhacker.ms.blog.comment.service.CommentService;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RequestMapping("/v1/blog/comments")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsApi {

    private final CommentService commentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Paging<Comment>> paging(CommentPagingCriteria criteria){
        return ResponseEntity.ok(commentService.findCommentsByBlogId(criteria));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteComment(@PathVariable String id, @RequestParam String operatorId){
        return ResponseEntity.ok(commentService.deleteCommentById(id, operatorId));
    }
}
