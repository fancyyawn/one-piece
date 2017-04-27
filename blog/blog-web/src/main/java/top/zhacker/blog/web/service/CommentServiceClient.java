package top.zhacker.blog.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.zhacker.blog.web.common.Paging;
import top.zhacker.blog.web.model.Comment;
import top.zhacker.blog.web.service.fallback.CommentServiceFallbackFactory;

import java.util.Map;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@FeignClient(value = "blog-comment", fallbackFactory = CommentServiceFallbackFactory.class)
@RequestMapping("/v1/blog/comments")
public interface CommentServiceClient {

    @RequestMapping(value = "", method = RequestMethod.GET)
    Paging<Comment> paging(@RequestParam Map<String,Object> criteria);

    @RequestMapping(value = "", method = RequestMethod.POST)
    String createComment(@RequestBody Comment comment);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Boolean deleteComment(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId);
}
