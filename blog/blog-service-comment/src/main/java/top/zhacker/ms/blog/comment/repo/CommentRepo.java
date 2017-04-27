package top.zhacker.ms.blog.comment.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.zhacker.ms.blog.comment.model.Comment;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment, String> {

    Page<Comment> findByPostId(String postId, Pageable pageable);
}
