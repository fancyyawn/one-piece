package top.zhacker.ms.blog.comment.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DATE: 17/1/5 上午10:01 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 用户领域类
 *
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -1575177945061174211L;

    private String id;

    private String username;

    private String avatar;

    private String email;

    private String gender;

    private String bio;
}
