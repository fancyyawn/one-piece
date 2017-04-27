package top.zhacker.ms.blog.post.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * DATE: 17/1/5 上午10:03 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客领域类，包含用户、评论等导航信息
 *
 */

@Document(collection = "posts")
@Data
@Accessors(chain = true)
public class Post implements Serializable {
    private static final long serialVersionUID = -2826939241838421885L;

    @Id
    private String id;

    private String authorId;

    private String title;

    private String content;

    private Integer pv; //访问数量

    private Date createdAt;

    private Date updatedAt;

    /**
     * 作者导航
     */
    private User author;
}
