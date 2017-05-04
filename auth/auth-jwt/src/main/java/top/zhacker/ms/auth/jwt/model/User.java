package top.zhacker.ms.auth.jwt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import top.zhacker.ms.auth.jwt.common.BaseUser;

import java.io.Serializable;

/**
 * DATE: 17/2/17 下午8:48 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
public class User implements BaseUser, Serializable {
    private static final long serialVersionUID = -2557434159485154612L;

    @Id
    private String id;
    private String name;
    private String password;
    private String displayName;
    private String avatar;
    private Integer type;
    private String roles;
}
