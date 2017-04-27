package top.zhacker.ms.guasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;
import co.paralleluniverse.strands.concurrent.CountDownLatch;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helloworld {
    @Suspendable
    static void m1() throws InterruptedException, SuspendExecution {
        String m = "m1";
        //System.out.println("m1 begin");
        m = m2();
        //System.out.println("m1 end");
        //System.out.println(m);
    }
    static String m2() throws SuspendExecution, InterruptedException {
        String m = m3();
        Strand.sleep(1000);
        return m;
    }
    //or define in META-INF/suspendables
    @Suspendable
    static String m3() {
        List l = Stream.of(1,2,3).filter(i -> i%2 == 0).collect(Collectors.toList());
        return l.toString();
    }
    static public void main(String[] args) throws ExecutionException, InterruptedException {
        int count = 10000;
        testThreadpool(count);
        testFiber(count);
    }
    static void testThreadpool(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        ExecutorService es = Executors.newFixedThreadPool(200);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            es.submit(() -> {
                long start = System.currentTimeMillis();
                try {
                    m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SuspendExecution suspendExecution) {
                    suspendExecution.printStackTrace();
                }
                start = System.currentTimeMillis() - start;
                latency.add(start);
                latch.countDown();
            });
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("thread pool took: " + t + ", latency: " + l + " ms");
        es.shutdownNow();
    }
    static void testFiber(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            new Fiber<Void>("Caller", new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    long start = System.currentTimeMillis();
                    m1();
                    start = System.currentTimeMillis() - start;
                    latency.add(start);
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("fiber took: " + t  + ", latency: " + l + " ms");
    }
}