package com.example.studydemos.framework.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgoDemo2025092804 {
    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1
     * 示例 1：
     *
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * 示例 2：
     *
     * 输入：triangle = [[-10]]
     * 输出：-10
     *
     * 输入：triangle =[[-1],[2,3],[1,-1,-3]]
     * 输出：-1
     */

    public int test(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 从倒数第二层开始向上遍历
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 当前节点值加上下层相邻节点的较小值
                int min = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(0).get(0);
    }

    public static void main(String[] args) {

        List<List<Integer> > triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(new AlgoDemo2025092804().test(triangle));
    }
}
