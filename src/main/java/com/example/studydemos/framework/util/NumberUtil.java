package com.example.studydemos.framework.util;

import org.springframework.util.NumberUtils;

import java.util.Arrays;
import java.util.Random;

public class NumberUtil extends NumberUtils {

    public static final int ZERO = 0;
    public static final int ONE = 1;

    /**
     * 大于
     *
     * @param number
     * @return
     */
    public static boolean gtZero(Long number) {
        return number != null && number > 0;
    }

    public static boolean gtZero(Integer number) {
        return number != null && number > 0;
    }

    /**
     * 等于0
     * @param number 要判断的数字
     * @return true or false
     */
    public static boolean eqZero(Long number) {
        return number != null && number == 0;
    }

    /**
     * 等于0
     * @param number 要判断的数字
     * @return true or false
     */
    public static boolean eqZero(Integer number) {
        return number != null && number == 0;
    }

    public static boolean isNullOrZero(Integer number) {
        return number == null || number == 0;
    }

    public static boolean isNullOrZero(Long number) {
        return number == null || number == 0;
    }

    /**
     * 大于等于
     *
     * @param number
     * @return
     */
    public static boolean geZero(Long number) {
        return number != null && number >= 0;
    }

    public static boolean geZero(Integer number) {
        return number != null && number >= 0;
    }

    /**
     * 获取0
     */
    public static Long getZero() {
        return 0L;
    }

    /**
     * 获取 [min,max]范围内的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(Integer min, Integer max) {
        if (min == null) {
            min = 0;
        }
        if (max == null) {
            max = 0;
        }
        if (min > max) {
            min = max;
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 判断一个整数是否在给定的范围内
     *
     * @param actual        整数
     * @param validStatuses 范围
     * @return
     */
    public static boolean in(Integer actual, Integer... validStatuses) {
        return Arrays.asList(validStatuses).contains(actual);
    }


    public static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    public static int convertExcelNumber(String number) {
        if(number.contains(".")){
            return Integer.parseInt(number.split("\\.")[0]);
        }
        return Integer.parseInt(number);
    }
}
