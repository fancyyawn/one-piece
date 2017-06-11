package top.zhacker.ms.reactor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RequestMapping("/flux")
public class Fluxs {

    private static final Random RANDOM = new Random();

    public void createFlux(){
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable( Arrays.asList("foo", "bar", "foobar"));
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 7);
        Flux<String> generator = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
        Flux<String> generator2 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();
                    return state;
                });

        Flux.create(fluxSink -> {
            String rand = String.valueOf(RANDOM.nextInt(10));
            if(rand.equals("0")) {
                fluxSink.complete();
            }else{
                fluxSink.next(rand);
            }
        });
    }

    @RequestMapping("/subscribe")
    public void subscribeFlux(){
        Flux<String> seq = Flux.just("foo", "bar", "foobar");
//        seq.subscribe();
//        seq.subscribe(System.out::println);
//        seq.subscribe(System.out::println, System.err::println);
        seq.subscribe(System.out::println, System.err::println, ()->System.out.println("complete successfully"));

//        subscribe(Consumer<? super T> consumer,
//                Consumer<? super Throwable> errorConsumer);
//
//        subscribe(Consumer<? super T> consumer,
//                Consumer<? super Throwable> errorConsumer,
//                Runnable completeConsumer);
//
//        subscribe(Consumer<? super T> consumer,
//                Consumer<? super Throwable> errorConsumer,
//                Runnable completeConsumer,
//                Consumer<? super Subscription> subscriptionConsumer);
    }
}
