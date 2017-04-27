package top.zhacker.ms.blog.post.service;

import top.zhacker.ms.blog.post.dto.PostPagingCriteria;
import top.zhacker.ms.blog.post.common.Paging;
import top.zhacker.ms.blog.post.model.Post;

/**
 * DATE: 17/3/24 下午1:37 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客服务
 */
public interface PostService {

    String createPost(Post post);

    Paging<Post> pagingPosts(PostPagingCriteria criteria);

    Post findPostById(String id, String operatorId);

    Boolean updatePostTitleAndContent(String id, String title, String content, String operatorId);

    Boolean deletePostById(String id, String operatorId);
}
