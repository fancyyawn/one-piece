package top.zhacker.ms.auth.jwt.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * DATE: 17/2/16 下午9:08 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
@Document
public class Me implements Serializable {
    private static final long serialVersionUID = 3239402875170104861L;

    private String content;
}
