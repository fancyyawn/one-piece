package top.zhacker.ms.blog.user.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATE: 2017/4/20 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RefreshScope
public class EchoApi {

    @Value("${msg}")
    String msg;

    @RequestMapping("/v1/blog/users/echo")
    public String echo(){
        return msg;
    }
}
