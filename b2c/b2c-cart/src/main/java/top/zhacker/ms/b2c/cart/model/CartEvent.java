package top.zhacker.ms.b2c.cart.model;

import lombok.Data;
import top.zhacker.ms.b2c.cart.common.BaseEntity;

import javax.persistence.*;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Data
@Entity
@Table(name = "cart_event",
        indexes = {@Index(name="IDX_CART_EVENT_USER", columnList = "id,userId")})
public class CartEvent extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CartEventType cartEventType;

    private Long userId;

    private String productId;

    private Integer quantity;
}
