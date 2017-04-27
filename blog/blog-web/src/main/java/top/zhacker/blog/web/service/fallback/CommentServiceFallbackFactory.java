package top.zhacker.blog.web.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.zhacker.blog.web.common.Paging;
import top.zhacker.blog.web.model.Comment;
import top.zhacker.blog.web.service.CommentServiceClient;

import java.util.Map;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Slf4j
@Component
public class CommentServiceFallbackFactory implements FallbackFactory<CommentServiceClient> {

    @Override
    public CommentServiceClient create(Throwable throwable) {

        return new CommentServiceClient() {
            @Override
            public Paging<Comment> paging(@RequestParam Map<String, Object> criteria) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public String createComment(@RequestBody Comment comment) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public Boolean deleteComment(@PathVariable("id") String id, @RequestParam("operatorId") String operatorId) {
                throw new RuntimeException(throwable.getMessage());
            }
        };
    }
}
