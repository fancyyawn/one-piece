package top.zhacker.ms.reactive.rxjava;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
public class TestRxjava {

    @Test
    public void test15(){

        Observable<List<Integer>> all = Observable
                .range(10, 20)
                .collect(ArrayList::new, List::add);
        all.subscribe(System.out::println);


        Observable<String> str = Observable
                .range(1, 10)
                .collect( StringBuilder::new,
                        (sb, x) -> sb.append(x).append(", "))
                .map(StringBuilder::toString);
        str.subscribe(System.out::println);
    }

    @Test
    public void test14(){
        Observable<BigDecimal> total = Observable.range(1,100)
                .map(x-> BigDecimal.valueOf(x.longValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        total.subscribe(System.out::println);

        Observable<BigDecimal> totalSequence = Observable.range(1,100)
                .map(x-> BigDecimal.valueOf(x.longValue()))
                .scan(BigDecimal.ZERO, BigDecimal::add);
        totalSequence.subscribe(System.out::println);
    }

    @Test
    public void test13(){
        Observable<BigInteger> factorials = Observable
                .range(2, 100)
                .scan(BigInteger.ONE, (big, cur) -> big.multiply(BigInteger.valueOf(cur)));
        factorials.subscribe(System.out::println);

    }

    private void log(Object obj){
        System.out.println(obj);
    }
    @Test
    public void test12(){
        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i)) .filter(i->i%3>0)
                .doOnNext(i -> System.out.println("B: " + i)) .map(i->"#"+i*10)
                .doOnNext(s -> System.out.println("C: " + s)) .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
    }

    @Test
    public void test11(){
        Observable
                .interval(1_000_000 / 60, MICROSECONDS)
                .subscribe((Long i) -> log(i));
        sleep(5, SECONDS);
    }

    @Test
    public void test10(){
        Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribe((Long zero) -> log(zero));
        sleep(5, SECONDS);
    }

    @Test
    public void test9(){
        rxLoad(0).subscribe(System.out::println);
    }

    Observable<Data> rxLoad(int id) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(load(id));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    Observable<Data> rxLoad2(int id) {
        return Observable.fromCallable(() -> load(id));
    }


    @Test
    public void test8(){
        loadAll(Arrays.asList(1, 2)).subscribe(System.out::println);
    }

    @ToString
    public static class Data{
        @Setter
        @Getter
        @Accessors(chain = true)
        private Integer id;
    }
    private Data load(Integer id){
        if(id.equals(0)){
            throw new RuntimeException("not.exist");
        }
        return new Data().setId(id);
    }

    Observable<Data> loadAll(Collection<Integer> ids) {
        return Observable.create(subscriber -> {
            ExecutorService pool = Executors.newFixedThreadPool(10);
            AtomicInteger countDown = new AtomicInteger(ids.size()); //DANGER, violates Rx contract. Don't do this!
            ids.forEach(id -> pool.submit(() -> {
                final Data data = load(id);
                subscriber.onNext(data);
                if (countDown.decrementAndGet() == 0) {
                    pool.shutdownNow();
                    subscriber.onCompleted();
                }
            }));
        });
    }


    @Test
    public void test7() throws InterruptedException {
        Observable<Integer> ints = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        sleep(5, SECONDS);
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(10);
                            subscriber.onCompleted();
                        }
                    };
                    Thread thread = new Thread(r);
                    thread.start();
                    subscriber.add(Subscriptions.create(thread::interrupt));
                });
        log("Starting");
        Subscription subscription = ints.subscribe(i -> log("Element A: " + i));
        TimeUnit.SECONDS.sleep(3L);
        subscription.unsubscribe();
        log("Exit");
        TimeUnit.SECONDS.sleep(10L);
    }

    static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException ignored) {
            //intentionally ignored
        }
    }

    @Test
    public void test6(){
        Observable<Integer> ints =
                Observable.<Integer>create(subscriber -> {
                    log("Create");
                    subscriber.onNext(42);
                    subscriber.onCompleted();
                } ).cache(); //cached
        log("Starting");
        ints.subscribe(i -> log("Element A: " + i));
        ints.subscribe(i -> log("Element B: " + i));
        log("Exit");
    }


    @Test
    public void test5(){
        Observable<Integer> ints =
                Observable.create(subscriber -> {
                    log("Create");
                    subscriber.onNext(42);
                    subscriber.onCompleted();
                } );
        log("Starting");
        ints.subscribe(i -> log("Element A: " + i));
        ints.subscribe(i -> log("Element B: " + i));
        log("Exit");
    }

    @Test
    public void test4() throws InterruptedException {
        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    Runnable r=()->{
                        BigInteger i = ZERO;
                        while (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(i);
                            i = i.add(ONE);
                        }
                    };
                    new Thread(r).start(); });

        Subscription subscription = naturalNumbers.subscribe(System.out::println); //after some time...
        SECONDS.sleep(1L);
        subscription.unsubscribe();
    }

    @Test
    public void test3() throws InterruptedException {
        Observable<String> a = Observable.create(s -> { new Thread(() -> {
            s.onNext("one");
            s.onNext("two");
            s.onCompleted();
        }).start();
        });
        Observable<String> b = Observable.create(s -> { new Thread(() -> {
            s.onNext("three");
            s.onNext("four");
            s.onCompleted();
        }).start();
        });
        // this subscribes to a and b concurrently,
        // and merges into a third sequential stream
         Observable<String> c = Observable.merge(a, b);

         c.subscribe(System.out::println);
    }

    @Test
    public void test2(){
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .map(String::hashCode)
                .map(i -> Integer.toString(i))
                .subscribe(System.out::println);
    }


    @Test
    public void test1(){
        Observable.just("Hello, world!").subscribe(System.out::println);
    }

    @Test
    public void test0(){
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        myObservable.subscribe(mySubscriber);
    }
}
