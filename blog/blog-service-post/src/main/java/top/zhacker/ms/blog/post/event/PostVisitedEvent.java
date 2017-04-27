package top.zhacker.ms.blog.post.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DATE: 17/1/10 下午10:32 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客访问事件
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostVisitedEvent implements Serializable {
    private static final long serialVersionUID = 5566971764380210366L;

    private String postId;

    private String visitorId;
}
