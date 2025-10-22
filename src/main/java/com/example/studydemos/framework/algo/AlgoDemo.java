package com.example.studydemos.framework.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoDemo {
    /**
     * java算法：给出数组m，给出目标值n，求一个组合，该组合由m中元素组合相加最接近n
     */
    private List<Integer> bestCombination;
    private int minDiff = Integer.MAX_VALUE;

    public List<Integer> findClosestSum(int[] nums, int target) {
        Arrays.sort(nums); // 优先处理大数
        backtrack(nums, target, new ArrayList<>(), 0, 0);
        return bestCombination;
    }

    private void backtrack(int[] nums, int target, List<Integer> current, int start, int sum) {
        int currentDiff = Math.abs(sum - target);

        // 更新最优解
        if (currentDiff < minDiff) {
            minDiff = currentDiff;
            bestCombination = new ArrayList<>(current);
        } else if (currentDiff == minDiff && current.size() > bestCombination.size()) {
            // 当差值相同时优先选择元素多的组合（更接近目标值）
            bestCombination = new ArrayList<>(current);
        }

        for (int i = start; i < nums.length; i++) {
            // 提前终止条件：当sum超过目标值且后续数字更大时停止
            if (sum + nums[i] > target + minDiff) break;

            current.add(nums[i]);
            backtrack(nums, target, current, i + 1, sum + nums[i]);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        int target = 100;
        List<Integer> result = new AlgoDemo().findClosestSum(nums, target);
        System.out.println("最接近的组合: " + result + " 和: " + result.stream().mapToInt(Integer::intValue).sum());
    }
}