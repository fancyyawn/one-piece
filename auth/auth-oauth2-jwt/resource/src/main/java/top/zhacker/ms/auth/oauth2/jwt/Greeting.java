package top.zhacker.ms.auth.oauth2.jwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * DATE: 2017/5/3 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
public class Greeting {

    @RequestMapping("/greeting")
    public Message home() {
        return new Message("Hello World");
    }

    static class Message {
        private String id = UUID.randomUUID().toString();
        private String content;

        Message() {
        }

        public Message(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }
}
