package top.zhacker.ms.reactive.rxjava.v1;

import org.junit.Test;
import rx.Observable;

import static top.zhacker.ms.reactive.rxjava.v1.Util.*;


public class BestPriceFinderTest {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    @Test
    public void findPricesSequentialTest(){
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
    }

    @Test
    public void findPricesParallelTest(){
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
    }

    @Test
    public void findPricesFutureTest(){
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
    }

    @Test
    public void findPricesReactiveTest(){
        executeRxJava("rxjava", bestPriceFinder.findPricesObservable("myPhone27s"));
    }

}
