package top.zhacker.ms.b2c.cart.model.product;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Data
public class Catalog {

    private Long id;

    private String name;

    private Set<Product> products = new HashSet<>();
}
