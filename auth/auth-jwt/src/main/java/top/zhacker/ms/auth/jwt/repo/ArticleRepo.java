package top.zhacker.ms.auth.jwt.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.zhacker.ms.auth.jwt.model.Article;

/**
 * DATE: 17/2/17 下午4:52 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
public interface ArticleRepo extends PagingAndSortingRepository<Article, String>{
}
