package top.zhacker.ms.b2c.cart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhacker.ms.b2c.cart.model.Cart;
import top.zhacker.ms.b2c.cart.model.CartEvent;

import java.util.Optional;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RequestMapping("/v1/cart")
public class CartApiV1 {

    private final CartServiceV1 cartServiceV1;

    @Autowired
    public CartApiV1(CartServiceV1 cartServiceV1) {
        this.cartServiceV1 = cartServiceV1;
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Long> addCartEvent(@RequestBody CartEvent cartEvent) throws Exception {
        return Optional.ofNullable(cartServiceV1.addCartEvent(cartEvent))
                .map(id -> new ResponseEntity<>(id, HttpStatus.OK))
                .orElseThrow(() -> new Exception("cart.not.find"));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Cart> getCart() throws Exception{
        return Optional.ofNullable(cartServiceV1.getShoppingCart())
                .map(cart -> new ResponseEntity<>(cart, HttpStatus.OK))
                .orElseThrow(() -> new Exception("cart.find.fail"));
    }

}
