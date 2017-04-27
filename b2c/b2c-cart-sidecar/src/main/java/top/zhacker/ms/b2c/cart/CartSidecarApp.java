package top.zhacker.ms.b2c.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * DATE: 2017/4/25 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@EnableSidecar
@SpringBootApplication
public class CartSidecarApp {
    public static void main(String[] args){
        SpringApplication.run(CartSidecarApp.class, args);
    }
}
