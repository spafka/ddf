package io.github.spafka.juc;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.spafka.flink.FutureUtil;
import org.apache.flink.api.common.time.Deadline;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.runtime.concurrent.FutureUtils;
import org.apache.flink.runtime.concurrent.ScheduledExecutorServiceAdapter;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Futures {

    static final Executor ex = Executors.newWorkStealingPool();
    static final ScheduledExecutorServiceAdapter sch = new ScheduledExecutorServiceAdapter(
            Executors.newScheduledThreadPool(10));

    @Test
    public void _1() {


        IntStream.rangeClosed(1, 1).forEach(i -> {


            CompletableFuture.runAsync(() -> {

                var f = FutureUtils.supplyAsync(() -> {

                    System.out.println(Thread.currentThread().getName());
                    return "logined" + i;
                }, ex);


                var s = f.whenCompleteAsync((x, e) -> {
                    System.out.println(Thread.currentThread().getName() + " refresh ....");
                }, ex);

                try {
                    String s1 = s.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                sch.scheduleAtFixedRate(() -> {
                    System.out.println("refresh...");
                }, 0, 1, TimeUnit.SECONDS);
            }, ex);

        });

        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.HOURS);

    }
}


