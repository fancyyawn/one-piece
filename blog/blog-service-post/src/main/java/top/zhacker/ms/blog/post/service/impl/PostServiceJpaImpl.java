package top.zhacker.ms.blog.post.service.impl;

import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.zhacker.ms.blog.post.common.PostServiceException;
import top.zhacker.ms.blog.post.dto.PostPagingCriteria;
import top.zhacker.ms.blog.post.event.PostVisitedEvent;
import top.zhacker.ms.blog.post.common.Paging;
import top.zhacker.ms.blog.post.model.Post;
import top.zhacker.ms.blog.post.repo.PostRepo;
import top.zhacker.ms.blog.post.service.PostService;

/**
 * DATE: 17/3/24 下午3:43 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客服务实现
 */
@Slf4j
@Service
public class PostServiceJpaImpl implements PostService {

    private final PostRepo postRepo;

    private final EventBus eventBus;

    @Autowired
    public PostServiceJpaImpl(PostRepo postRepo, EventBus eventBus) {
        this.postRepo = postRepo;
        this.eventBus = eventBus;
    }

    @Override
    public String createPost(Post post) {
        if(post.getAuthor() == null){
            log.error("post author required, post={}", post);
            throw new PostServiceException("post.author.required");
        }
        try {
            post.setPv(0);
            postRepo.save(post);
            return post.getId();

        }catch (Exception e){
            log.error("createPost fail, post={}, cause={}", post, Throwables.getStackTraceAsString(e));
            throw new PostServiceException("post.create.fail");
        }
    }

    @Override
    public Paging<Post> pagingPosts(PostPagingCriteria criteria) {

        try {
            Page<Post> postEntities;

            //根据作者ID进行分页查询
            Pageable pageable = new PageRequest(criteria.getPageNo(), criteria.getPageSize(), Sort.Direction.DESC, "id");
            if(criteria.getAuthorId()!=null) {
                postEntities = postRepo.findByAuthorId(criteria.getAuthorId(), pageable);
            }else{
                postEntities = postRepo.findAll(pageable);
            }

            return new Paging<>(postEntities.getTotalElements(), postEntities.getContent());

        }catch (Exception e){
            log.error("pagingPosts posts fail, criteria={}, cause={}", criteria, Throwables.getStackTraceAsString(e));
            throw new PostServiceException("post.paging.fail");
        }
    }

    @Override
    public Post findPostById(String id, String operatorId) {
        try {

            Post post =  postRepo.findOne(id);

            eventBus.post(new PostVisitedEvent(id, operatorId));
            return post;
        }catch (Exception e){
            log.error("findPostById fail, id={}, cause={}", id, Throwables.getStackTraceAsString(e));
            throw new PostServiceException("post.find.by.id.fail");
        }
    }

    @Override
    public Boolean updatePostTitleAndContent(String id, String title, String content, String operatorId) {
        try {
            Post entity = postRepo.findOne(id);
            if(entity==null){
                log.warn("update post fail, because post[{}] not exist", id);
                return Boolean.FALSE;
            }
            entity.setTitle(title);
            entity.setContent(content);
            postRepo.save(entity);

            return Boolean.TRUE;

        }catch (Exception e){
            log.error("updatePostTitleAndContent fail, id={}, title={}, content={}, cause={}",
                    id, title, content, Throwables.getStackTraceAsString(e));
            throw new PostServiceException("post.update.fail");
        }
    }

    @Override
    public Boolean deletePostById(String id, String operatorId) {
        try{
            Post entity = postRepo.findOne(id);
            if(entity==null){
                log.warn("delete post fail, because post[{}] not exist", id);
                return Boolean.FALSE;
            }
            postRepo.delete(id);

            return Boolean.TRUE;
        }catch (Exception e){
            log.error("deletePostById fail, id={}, cause={}", id, Throwables.getStackTraceAsString(e));
            throw new PostServiceException("post.delete.fail");
        }
    }
}
