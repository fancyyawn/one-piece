package top.zhacker.ms.blog.post.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.zhacker.ms.blog.post.model.Post;

/**
 * DATE: 17/3/24 下午2:05 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客存储
 */
@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, String> {

    Page<Post> findByAuthorId(String authorId, Pageable pageable);
}
