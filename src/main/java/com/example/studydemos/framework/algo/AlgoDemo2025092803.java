package com.example.studydemos.framework.algo;

import java.util.Arrays;

public class AlgoDemo2025092803 {
    /**
     *给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
     */

    public int test(int[] points) {
        int num = 0;
        if (points.length < 3)
            return num;
        Arrays.sort(points);
        for (int i = 0; i < points.length - 2; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {

//                for (int k = j + 1; k < points.length; k++) {
//                    if (points[i] + points[j] > points[k])
//                        num++;
//                }
            }
        }
        return num;
    }

    public static void main(String[] args) {

        int []nums = {2,2,3,2,3,2,3,2,3,4};
        System.out.println(new AlgoDemo2025092803().test(nums));
    }
}
