package top.zhacker.ms.blog.user.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.zhacker.ms.blog.user.model.User;

/**
 * DATE: 17/3/24 下午2:05 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 用户存储
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
