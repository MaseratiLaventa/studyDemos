package com.example.studydemos.framework.util;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private final long capacity;          // 桶总容量
    private final AtomicLong tokens;      // 当前令牌数
    private final long refillRate;        // 令牌填充速率(个/秒)
    private volatile long lastRefillTime; // 上次填充时间戳(毫秒)

    public TokenBucket(long capacity, long refillRate) {
        this.capacity = capacity;
        this.tokens = new AtomicLong(capacity);
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire(long permits) {
        refillTokens();
        long current = tokens.get();
        if (current < permits) {
            return false;
        }
        return tokens.compareAndSet(current, current - permits);
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTime;
        long newTokens = elapsedTime * refillRate / 1000;

        if (newTokens > 0) {
            lastRefillTime = now;
            tokens.updateAndGet(current -> Math.min(capacity, current + newTokens));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(1000, 1000); // 容量1000，每秒1000个

        // 测试突发请求
        for (int i = 0; i < 20; i++) {
            System.out.println("Request " + i + ": " +
                    (bucket.tryAcquire(100) ? "Accepted" : "Denied"));
            //Thread.sleep(50);
        }
    }
}
