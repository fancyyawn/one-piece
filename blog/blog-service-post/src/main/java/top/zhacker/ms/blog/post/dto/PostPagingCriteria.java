package top.zhacker.ms.blog.post.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import top.zhacker.ms.blog.post.common.PagingCriteria;

/**
 * DATE: 17/1/5 上午10:05 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客分页条件
 */
@Data
@Accessors(chain = true)
public class PostPagingCriteria implements PagingCriteria {

    private Integer pageSize;
    private Integer pageNo;
    private String authorId;

    public Integer getPageSize(){
        return pageSize==null? 10 : pageSize;
    }

    public Integer getPageNo(){
        return pageNo==null? 0 : pageNo;
    }
}
