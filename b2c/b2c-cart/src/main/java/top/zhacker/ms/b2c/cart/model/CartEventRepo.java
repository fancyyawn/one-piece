package top.zhacker.ms.b2c.cart.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Repository
public interface CartEventRepo extends CrudRepository<CartEvent, Long> {

    Stream<CartEvent> findByUserId(Long userId);
}
