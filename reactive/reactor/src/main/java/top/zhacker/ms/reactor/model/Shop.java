package top.zhacker.ms.reactor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@AllArgsConstructor
public class Shop {

    @Getter
    @NonNull
    private String name;

    private static Random random = new Random();

    public double getPrice(String product){
        return calculatePrice(product);
    }

    private double calculatePrice(String product){
        delay();
        if(product.equals("p0")){
            throw new RuntimeException("product.not.exist");
        }
        return random.nextDouble()* product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(()-> calculatePrice(product));
    }

    public Future<Double> getPriceAsync_1(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()-> {
            try {
                futurePrice.complete(calculatePrice(product));
            }catch (Exception e){
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }




    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
