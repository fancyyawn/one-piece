package top.zhacker.blog.web.controller;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.zhacker.blog.web.common.Paging;
import top.zhacker.blog.web.dto.CommentPagingCriteria;
import top.zhacker.blog.web.dto.PostPagingCriteria;
import top.zhacker.blog.web.model.Post;
import top.zhacker.blog.web.service.CommentServiceClient;
import top.zhacker.blog.web.service.PostServiceClient;

/**
 * DATE: 17/1/5 上午10:55 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客视图
 */
@Slf4j
@Controller
@RequestMapping("/posts")
public class Posts extends ViewBase {

    private final PostServiceClient postService;

    private final CommentServiceClient commentService;

    @Autowired
    public Posts(PostServiceClient postService, CommentServiceClient commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String list(@RequestParam(required = false) Long author,
                       @RequestParam(defaultValue = "0") Integer pageNo,
                       @RequestParam(defaultValue = "20") Integer pageSize,
                       Model model){

        try{
            Paging<Post> respPaging = postService.paging(new PostPagingCriteria()
                    .setAuthorId(author).setPageNo(pageNo).setPageSize(pageSize).toMap());
            model.addAttribute("posts", respPaging.getData());
            return "posts/list";

        }catch (Exception e){
            log.error("paging posts fail,  cause={}", Throwables.getStackTraceAsString(e));
            model.addAttribute("error", "paging.post.fail");
            return "error";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPost(){
        return "posts/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPost(Post post, RedirectAttributes redirectAttributes){

        post.setAuthorId(getCurrentUserId());
        post.setAuthor(getCurrentUser());
        try{

            String resp = postService.createPost(post);

            redirectAttributes.addFlashAttribute("success", "发表成功");
            return "redirect:/posts/" + resp;
        }catch (Exception e){
            log.error("createPost fail, post={}, cause={}", post, Throwables.getStackTraceAsString(e));
            redirectAttributes.addFlashAttribute("error", "create.post.fail");
            return "posts/create";
        }
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public String findById(@PathVariable("postId") String postId, Model model){

        try {
            Post postResp = postService.findPost(postId, getCurrentUserId());
            postResp.setComments(commentService.paging(
                    new CommentPagingCriteria().setPostId(postId).setPageNo(0).setPageSize(100).toMap()));

            model.addAttribute("post", postResp);
            return "posts/detail";

        }catch (Exception e){
            log.error("find post fail,  cause={}", Throwables.getStackTraceAsString(e));
            model.addAttribute("error", "find.post.fail");
            return "error";
        }
    }

    @RequestMapping(value = "/{postId}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable("postId") String postId, Model model){
        try {
            Post respPost = postService.findPost(postId, getCurrentUserId());
            model.addAttribute("post", respPost);
            return "posts/edit";
        }catch (Exception e){
            log.error("edit post fail,  cause={}", Throwables.getStackTraceAsString(e));
            model.addAttribute("error", "find.post.fail");
            return "error";
        }
    }


    @RequestMapping(value = "/{postId}/edit", method = RequestMethod.POST)
    public String editPost(@PathVariable("postId") String postId,
                       String title, String content, RedirectAttributes attributes){
        try {
            postService.updatePost(postId, title, content, getCurrentUserId());

            attributes.addFlashAttribute("success", "post.edit.success");
            return "redirect:/posts/" + postId;
        }catch (Exception e){
            log.error("edit post fail,  cause={}", Throwables.getStackTraceAsString(e));
            attributes.addFlashAttribute("error", "find.post.fail");
            return "posts/edit";
        }
    }

    @RequestMapping(value = "/{postId}/remove", method = RequestMethod.POST)
    public String removePost(@PathVariable("postId") String postId, RedirectAttributes attributes){

        try {
            postService.deletePost(postId, getCurrentUserId());

            attributes.addFlashAttribute("success", "post.remove.success");
            return "redirect:/posts";
        }catch (Exception e){
            log.error("remove post fail,  cause={}", Throwables.getStackTraceAsString(e));
            attributes.addFlashAttribute("error", "remove.post.fail");
            return "posts/list";
        }
    }

}
