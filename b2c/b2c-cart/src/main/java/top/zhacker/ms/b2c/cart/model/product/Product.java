package top.zhacker.ms.b2c.cart.model.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Data
public class Product {

    private Long id;
    private String name;
    private String productId;
    private String description;
    private BigDecimal unitPrice;
}
