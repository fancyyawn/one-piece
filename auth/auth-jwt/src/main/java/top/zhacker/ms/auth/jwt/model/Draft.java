package top.zhacker.ms.auth.jwt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DATE: 17/2/16 下午8:53 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Data
@Document
public class Draft implements Serializable {
    private static final long serialVersionUID = 8512485539023072671L;

    @Id
    private String id;
    private String title;
    private String excerpt;
    private String content;
    @DBRef
    private List<Tag> tags;
    private Date createTime;
    private Date lastEditTime;
    private Article article;
    private Boolean draftPublished;
}
