package top.zhacker.ms.auth.jwt.model;

import lombok.Data;

import java.io.Serializable;

/**
 * DATE: 17/2/17 下午5:55 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
public class Token implements Serializable{
    private static final long serialVersionUID = 3981677305774533077L;

    private String userId;

    private String userName;
}
