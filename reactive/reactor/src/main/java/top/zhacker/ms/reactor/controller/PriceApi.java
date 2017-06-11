package top.zhacker.ms.reactor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhacker.ms.reactor.model.Shop;
import top.zhacker.ms.reactor.service.PriceService;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@Slf4j
public class PriceApi {

    @Autowired
    private PriceService priceService;

    @RequestMapping("/api/shops/1/price")
    public double price(@RequestParam String product){
        Shop shop = new Shop("shop1");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync(product);
        log.info("invoke return after {}", (System.nanoTime()-start)/1000_000);

        doSomething();

        try {
            double price = futurePrice.get(5L, TimeUnit.SECONDS);
            log.info("price is {}", price);
            log.info("Price returned after {}", (System.nanoTime()-start) / 1000_000);
            return price;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/api/shops/price")
    public void shopsPrice(@RequestParam String product){
        long start = System.nanoTime();
        log.info("{}", priceService.findPricesParallel(product));
        log.info("done in {}", (System.nanoTime()-start)/1000_000);
    }

    @RequestMapping("/api/shops/price/async")
    public void shopsPriceAsync(@RequestParam String product){
        long start = System.nanoTime();
        log.info("{}", priceService.findPricesAsync(product));
        log.info("done in {}", (System.nanoTime()-start)/1000_000);
    }

    private void doSomething(){
        log.info("do some thing else...");
    }
}
