package com.example.studydemos.project.completed;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletedFutureTest {


    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static int priceOfTb() {
        delay();
        System.out.println("tb");
        return 100;
    }

    private static int priceOfjd() {
        delay();
        System.out.println("jd");
        return 100;
    }

    private static int priceOftm() {
        delay();
        System.out.println("tm");
        return 100;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> tb = CompletableFuture.supplyAsync(CompletedFutureTest::priceOfTb);
        CompletableFuture<Integer> jd = CompletableFuture.supplyAsync(CompletedFutureTest::priceOfjd);
        CompletableFuture<Integer> tm = CompletableFuture.supplyAsync(CompletedFutureTest::priceOftm);
        CompletableFuture.allOf(tb, jd, tm).join();
        System.out.println(tb.getNow(0) + jd.getNow(0) + tm.getNow(0));
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        priceOfjd();
        priceOfTb();
        priceOftm();
        System.out.println("cost time:" + (System.currentTimeMillis() - start));

    }
}
