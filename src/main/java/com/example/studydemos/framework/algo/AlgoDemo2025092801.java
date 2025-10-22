package com.example.studydemos.framework.algo;

import java.util.Arrays;

public class AlgoDemo2025092801 {
    /**
     * 给定由一些正数（代表长度）组成的数组 nums ，返回 由其中三个长度组成的、面积不为零的三角形的最大周长 。如果不能形成任何面积不为零的三角形，返回 0
     */

    public int test(int[] nums) {
        // 三个边，最大边<其他边之和
        if (nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] + nums[i + 1] > nums[i + 2]) {
                maxSum = Math.max(maxSum, nums[i] + nums[i + 1] + nums[i + 2]);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 10};
        System.out.println(new AlgoDemo2025092801().test(nums));
    }
}
