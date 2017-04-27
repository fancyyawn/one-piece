package top.zhacker.ms.b2c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * DATE: 2017/4/25 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@EnableZuulProxy
@SpringBootApplication
public class B2cZuulApp {
    public static void main(String[] args){
        SpringApplication.run(B2cZuulApp.class, args);
    }
}
