package top.zhacker.blog.web.model;

import lombok.Data;
import lombok.experimental.Accessors;
import top.zhacker.blog.web.common.Paging;

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
@Data
@Accessors(chain = true)
public class Post implements Serializable {
    private static final long serialVersionUID = -2826939241838421885L;

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

    private Paging<Comment> comments;
}
