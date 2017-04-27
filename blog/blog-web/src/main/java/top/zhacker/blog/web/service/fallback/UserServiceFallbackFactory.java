package top.zhacker.blog.web.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import top.zhacker.blog.web.model.User;
import top.zhacker.blog.web.service.UserServiceClient;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Component
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceClient() {

            @Override
            public Long createUser(@RequestBody User user) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public User findUserById(@PathVariable("id") Long id) {
                throw new RuntimeException(throwable.getMessage());
            }

            @Override
            public User findUserByName(@PathVariable("name") String name) {
                throw new RuntimeException(throwable.getMessage());
            }
        };
    }
}
