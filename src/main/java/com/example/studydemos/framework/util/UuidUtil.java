package com.example.studydemos.framework.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.*;

/**
 * JUG 是一组用于处理 UUID 的 Java 类：使用任何标准方法生成 UUID、高效输出、排序等。它根据UUID 规范 (RFC-9562)生成 UUID （有关更多解释，请参阅Wikipedia UUID 页面）
 * JUG 最初由 Tatu Saloranta ( tatu.saloranta@iki.fi ) 于 2002 年编写，多年来一直在更新。此外，许多其他人也帮助修复了错误并实现了新功能：请参阅release-notes/CREDITS完整列表。
 * JUG 在Apache License 2.0下获得许可
 * <p>
 * <p>
 * <p>
 * 支持的 UUID 版本（1、3、4、5、6、7）
 * <p>
 * JUG 支持RFC 4122中定义的两个“经典”版本：
 * 1: 基于时间/地点
 * 3和5：基于名称哈希
 * 4：基于随机数
 * <p>
 * 以及新（2022-）提出的（参见uuid6和RFC-9562变体：
 * 6：重新排序的版本1（按字典顺序排列）
 * 7：Unix 时间戳 + 基于随机的变体（也按字典顺序排列）
 */
public class UuidUtil {

    /**
     * UUID uuid = Generators.timeBasedGenerator().generate(); // Version 1
     * UUID uuid = Generators.randomBasedGenerator().generate(); // Version 4
     * UUID uuid = Generators.nameBasedgenerator().generate("string to hash"); // Version 5
     * // With JUG 4.1+: support for https://github.com/uuid6/uuid6-ietf-draft versions 6 and 7:
     * UUID uuid = Generators.timeBasedReorderedGenerator().generate(); // Version 6
     * UUID uuid = Generators.timeBasedEpochGenerator().generate(); // Version 7
     * // With JUG 5.0 added variation:
     * UUID uuid = Generators.timeBasedEpochRandomGenerator().generate(); // Version 7 with per-call random values
     */

    public static String getUuidV1() {
        UUID uuid = Generators.timeBasedGenerator().generate(); // Version 1
        return uuid.toString();
    }

    public static String getUuidV4() {
        UUID uuid = Generators.randomBasedGenerator().generate(); // Version 4
        return uuid.toString();
    }

    public static String getUuidV6() {
        UUID uuid = Generators.timeBasedReorderedGenerator().generate(); // Version 6
        return uuid.toString();
    }

    /**
     * UUID v7 的格式中，前6位定义了版本号和变体号，剩余74位通过随机生成确保唯一性。
     * UUID v7 的结构比 UUID v1 和 UUID v6 更加简单，专注于提供易于实现的时间戳和随机数据的组合。UUIDv7 包含一个48位的 Unix 时间戳，后面跟着一部分版本和随机数据，整体结构更加清晰。
     * UUIDv1 包含了设备的 MAC 地址，这在隐私和安全性上带来了风险。UUIDv7 不再使用 MAC 地址，改为完全依赖随机数生成器和时间戳，从而提高了安全性。
     * 设计为时间有序，可以直接按字节比较进行排序。
     *
     * 由于它的时间戳是以毫秒为单位的，因此在数据库索引中的插入顺序更为合理，可以提高插入性能，而 UUIDv1 和 UUIDv6 的时间戳分散在不同的字段中，导致在数据库中插入时索引局部性较差。
     * @return
     */
    public static String getUuidV7() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        UUID uuid = Generators.timeBasedEpochGenerator().generate(); // Version 7
        return uuid.toString();
    }

    public static String getUUID() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getUuidV7();
    }

    /**
     * 一次生成多个v7版本的
     * @param num
     * @return
     */
    public static List<String> getUuidV7Multi(int num) {
        //生成器是完全线程安全的，因此单个实例可以在多个线程之间共享。
        TimeBasedEpochGenerator gen = Generators.timeBasedEpochGenerator();
        List<String> list = Lists.newArrayListWithCapacity(num);
        for (int i = 0; i < num; i++) {
            list.add(gen.generate().toString());
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {

        int num = 10000;
        long start = System.currentTimeMillis();
//        getUuidV7Multi(num);
//        long end = System.currentTimeMillis();
//        long time = end - start;
//        System.out.println("使用Multi耗时：" + time);
//
//        System.out.println("=====================");
//
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < num; i++) {
////           System.out.println( getUuidV7());
//             getUuidV7();
//        }
//        long end1 = System.currentTimeMillis();
//
//        System.out.println("使用单个耗时：" + (end1 - start1));
//
//        System.out.println("=====================");

//        long start2 = System.currentTimeMillis();
//        for (int i = 0; i < num; i++) {
////            System.out.println(UUID.randomUUID());
//           //UUID.randomUUID();
//        }
//        long end2 = System.currentTimeMillis();
//        System.out.println("耗时：" + (end2 - start2));



        int  threadNum = 100;
        int  everyThreadNum = 10000;
        Set<String> orderNos = Collections.synchronizedSet(new HashSet<>());
//        CountDownLatch latch = new CountDownLatch(threadNum);

//        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
//        for (int i = 0; i < threadNum; i++) {
//            executor.submit(() -> {
//                for (int j = 0; j < everyThreadNum; j++) {
//
//                }
//                latch.countDown();
//            });
//        }
        String resultd= getUuidV7().replaceAll("-","");
        System.out.println(resultd);
        orderNos.add(resultd);
//        latch.await();
//        executor.shutdown();
        System.out.println(orderNos.size()+"条");
        System.out.println("耗时"+(System.currentTimeMillis()-start));
    }
}
