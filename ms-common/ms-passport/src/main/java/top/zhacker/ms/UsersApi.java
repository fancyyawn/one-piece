package top.zhacker.ms;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * DATE: 2017/4/27 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
public class UsersApi {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
