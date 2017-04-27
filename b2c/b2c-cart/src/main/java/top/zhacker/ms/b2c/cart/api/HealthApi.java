package top.zhacker.ms.b2c.cart.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * DATE: 2017/4/25 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@Slf4j
public class HealthApi {

    @RequestMapping("/health.json")
    public Map<String, String> health(){
        log.info("health...");
        return Collections.singletonMap("status", "UP");
    }
}
