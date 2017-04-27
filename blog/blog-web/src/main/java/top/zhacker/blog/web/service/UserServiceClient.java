package top.zhacker.blog.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.zhacker.blog.web.model.User;
import top.zhacker.blog.web.service.fallback.UserServiceFallbackFactory;

/**
 * DATE: 17/1/13 下午3:48 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@FeignClient(value = "blog-user", fallbackFactory = UserServiceFallbackFactory.class)
@RequestMapping("/v1/blog/users")
public interface UserServiceClient {

    @RequestMapping(value = "", method = RequestMethod.POST)
    Long createUser(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findUserById(@PathVariable("id") Long id);

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    User findUserByName(@PathVariable("name") String name);

}
