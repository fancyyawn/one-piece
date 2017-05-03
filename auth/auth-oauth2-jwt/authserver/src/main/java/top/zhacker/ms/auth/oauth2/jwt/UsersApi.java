package top.zhacker.ms.auth.oauth2.jwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * DATE: 2017/4/30 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
public class UsersApi {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
