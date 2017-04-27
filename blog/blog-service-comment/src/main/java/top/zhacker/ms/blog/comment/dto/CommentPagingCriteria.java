package top.zhacker.ms.blog.comment.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import top.zhacker.ms.blog.comment.common.PagingCriteria;

import javax.validation.constraints.NotNull;

/**
 * DATE: 17/1/5 上午10:05 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 博客分页条件
 */
@Data
@Accessors(chain = true)
public class CommentPagingCriteria implements PagingCriteria {

    private Integer pageSize;
    private Integer pageNo;

    @NotNull
    private String postId;

    public Integer getPageSize(){
        return pageSize==null? 10 : pageSize;
    }

    public Integer getPageNo(){
        return pageNo==null? 0 : pageNo;
    }
}
