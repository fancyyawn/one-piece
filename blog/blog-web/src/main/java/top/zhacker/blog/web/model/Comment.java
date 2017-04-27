package top.zhacker.blog.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * DATE: 17/1/5 上午10:05 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 评论领域类
 */
@Data
@Accessors(chain = true)
public class Comment implements Serializable {
    private static final long serialVersionUID = 8343096061678282178L;

    private String id;

    private String postId;

    private String authorId;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 作者导航
     */
    private User author;

    /**
     * Post导航
     */
    private Post post;

}
