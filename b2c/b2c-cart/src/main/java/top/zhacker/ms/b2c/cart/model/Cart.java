package top.zhacker.ms.b2c.cart.model;

import lombok.Data;
import reactor.core.publisher.Flux;
import top.zhacker.ms.b2c.cart.model.product.Catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * DATE: 2017/4/24 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Data
public class Cart {
    private Map<String, Integer> productMap = new HashMap<>();
    private List<LineItem> lineItems = new ArrayList<>();
    private Catalog catalog;

    public Cart incorporate(CartEvent cartEvent) {
        // Remember that thing about safety properties in microservices?
        Flux<CartEventType> validCartEventTypes =
                Flux.fromStream(Stream.of(CartEventType.ADD_ITEM,
                        CartEventType.REMOVE_ITEM));

        // The CartEvent's type must be either ADD_ITEM or REMOVE_ITEM
        if (validCartEventTypes.any(cartEventType ->
                cartEvent.getCartEventType().equals(cartEventType)).block()) {
            // Update the aggregate view of each line item's quantity from the event type
            productMap.put(cartEvent.getProductId(),
                    productMap.getOrDefault(cartEvent.getProductId(), 0) +
                            (cartEvent.getQuantity() * (cartEvent.getCartEventType()
                                    .equals(CartEventType.ADD_ITEM) ? 1 : -1)));
        }

        // Return the updated state of the aggregate to the reactive stream's reduce method
        return this;
    }
}
