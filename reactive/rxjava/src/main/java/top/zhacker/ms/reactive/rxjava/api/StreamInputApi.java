package top.zhacker.ms.reactive.rxjava.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATE: 2017/4/25 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
public class StreamInputApi {

    @Autowired
    @Qualifier("output")
    private MessageChannel source;

    @RequestMapping("/api/stream")
    public void sendMessage(@RequestParam String msg){
        source.send(MessageBuilder.withPayload(msg).build());
    }

}
