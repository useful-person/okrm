package com.example.demo.utils;

/**
 * 实用的字符串操作类。
 */
public class StringUtils {

    /**
     * 将输入字符串转换为大写。
     *
     * @param input 要转换为大写的字符串
     * @return 输入字符串的大写版本，如果输入为null则返回null
     */
    public static String toUpperCase(String input) {
        return input != null ? input.toUpperCase() : null;
    }

    /**
     * 反转输入字符串。
     *
     * @param input 要反转的字符串
     * @return 反转后的字符串版本，如果输入为null则返回null
     */
    public static String reverse(String input) {
        if (input == null)
            return null;
        return new StringBuilder(input).reverse().toString();
    }
}
