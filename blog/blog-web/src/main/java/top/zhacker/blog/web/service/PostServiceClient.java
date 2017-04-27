package top.zhacker.blog.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.zhacker.blog.web.common.Paging;
import top.zhacker.blog.web.model.Post;
import top.zhacker.blog.web.service.fallback.PostServiceFallbackFactory;

import java.util.Map;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */

@FeignClient(value = "blog-post", fallbackFactory = PostServiceFallbackFactory.class)
@RequestMapping("/v1/blog/posts")
public interface PostServiceClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    String createPost(@RequestBody Post post);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Boolean deletePost(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId);


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Boolean updatePost(@PathVariable("id") String id,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("operatorId") String operatorId);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Post findPost(@PathVariable("id") String id, @RequestParam(value = "operatorId", required = false) String operatorId);

    @RequestMapping(value = "", method = RequestMethod.GET)
    Paging<Post> paging(@RequestParam Map<String, Object> criteria);
}
