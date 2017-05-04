package top.zhacker.ms.auth.jwt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * DATE: 17/2/16 下午8:57 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
@Document
public class Tag implements Serializable {
    private static final long serialVersionUID = 4370849280409345452L;

    @Id
    private String id;
    private String name;
}
