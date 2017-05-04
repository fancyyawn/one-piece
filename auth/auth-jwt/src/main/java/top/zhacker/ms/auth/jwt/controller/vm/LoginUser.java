package top.zhacker.ms.auth.jwt.controller.vm;

import lombok.Data;

import java.io.Serializable;

/**
 * DATE: 17/2/17 下午6:10 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -2923448274833032096L;

    private String username;
    private String password;
}
