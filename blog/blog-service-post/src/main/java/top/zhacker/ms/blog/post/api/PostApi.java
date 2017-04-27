package top.zhacker.ms.blog.post.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.zhacker.ms.blog.post.dto.PostPagingCriteria;
import top.zhacker.ms.blog.post.common.Paging;
import top.zhacker.ms.blog.post.model.Post;
import top.zhacker.ms.blog.post.service.PostService;

/**
 * DATE: 17/1/5 上午10:55 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客视图
 */
@RestController
@RequestMapping("/v1/blog/posts")
public class PostApi{

    private final PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> createPost(@RequestBody Post post){
        return ResponseEntity.ok(postService.createPost(post));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deletePost(@PathVariable String id,
                              @RequestParam String operatorId){
        return ResponseEntity.ok(postService.deletePostById(id, operatorId));
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updatePost(@PathVariable String id,
                              @RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String operatorId){
        return ResponseEntity.ok(postService.updatePostTitleAndContent(id, title, content, operatorId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findPost(@PathVariable String id, @RequestParam(required = false) String operatorId){
        return ResponseEntity.ok(postService.findPostById(id, operatorId));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Paging<Post>> paging(PostPagingCriteria criteria){

        return ResponseEntity.ok(postService.pagingPosts(criteria));
    }
}
