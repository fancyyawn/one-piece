package top.zhacker.ms.reactor.service;

import lombok.val;
import org.springframework.stereotype.Service;
import top.zhacker.ms.reactor.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Service
public class PriceService {


    private List<Shop> shops = new ArrayList<>();
    private final Executor executor;
    {
        IntStream.range(0, 200).forEach((i)-> shops.add(new Shop("shop"+i)));

        executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                });
    }

    public List<String> findPrices(String product){
        return shops.stream()
                .map(shop -> String .format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesParallel(String product){
        return shops.parallelStream()
                .map(shop -> String .format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }


    public List<String> findPricesAsync(String product){
        val priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        ()-> String .format("%s price is %.2f", shop.getName(), shop.getPrice(product)),executor))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

}
