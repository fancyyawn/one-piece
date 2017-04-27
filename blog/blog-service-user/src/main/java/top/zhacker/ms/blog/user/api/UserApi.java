package top.zhacker.ms.blog.user.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.zhacker.ms.blog.user.model.User;
import top.zhacker.ms.blog.user.service.UserService;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@Slf4j
@RequestMapping("/v1/blog/users")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.findUserByName(name));
    }
}
