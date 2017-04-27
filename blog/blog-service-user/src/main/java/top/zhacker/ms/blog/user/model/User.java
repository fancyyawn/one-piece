package top.zhacker.ms.blog.user.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * DATE: 17/1/5 上午10:01 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 用户领域类
 *
 */
@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -1575177945061174211L;

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String gender;

    private String bio;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
