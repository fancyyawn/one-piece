package top.zhacker.ms.b2c.cart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhacker.ms.b2c.cart.model.Cart;
import top.zhacker.ms.b2c.cart.model.CartEvent;
import top.zhacker.ms.b2c.cart.model.CartEventRepo;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Service
public class CartServiceV1 {

    private final CartEventRepo cartEventRepo;

    @Autowired
    public CartServiceV1(CartEventRepo cartEventRepo) {
        this.cartEventRepo = cartEventRepo;
    }

    public Long addCartEvent(CartEvent event){
        event.setUserId(1L); //todo
        cartEventRepo.save(event);
        return event.getId();
    }

    public Cart getShoppingCart() throws Exception{

        return new Cart();
    }
}
