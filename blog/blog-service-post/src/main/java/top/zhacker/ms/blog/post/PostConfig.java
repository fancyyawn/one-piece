package top.zhacker.ms.blog.post;

import com.google.common.eventbus.EventBus;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Configuration
@EnableEurekaClient
public class PostConfig {

    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }
}
