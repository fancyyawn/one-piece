package top.zhacker.blog.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import top.zhacker.blog.web.common.PagingCriteria;

import java.util.HashMap;
import java.util.Map;

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
    private Long authorId;

    public Integer getPageSize(){
        return pageSize==null? 10 : pageSize;
    }

    public Integer getPageNo(){
        return pageNo==null? 0 : pageNo;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("authorId", authorId);
        map.put("pageSize", pageSize);
        map.put("pageNo", pageNo);
        return map;
    }
}
