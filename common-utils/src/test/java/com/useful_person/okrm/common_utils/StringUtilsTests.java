package com.useful_person.okrm.common_utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StringUtils 工具类测试")
class StringUtilsTest {

    @Test
    @DisplayName("toUpperCase: 正常字符串应转换为大写")
    void testToUpperCase_withNormalString() {
        assertEquals("HELLO", StringUtils.toUpperCase("hello"));
    }

    @Test
    @DisplayName("toUpperCase: 混合大小写字符串应转换为全大写")
    void testToUpperCase_withMixedCase() {
        assertEquals("HELLO", StringUtils.toUpperCase("HeLLo"));
    }

    @Test
    @DisplayName("toUpperCase: null 输入应返回 null")
    void testToUpperCase_withNull() {
        assertNull(StringUtils.toUpperCase(null));
    }

    @Test
    @DisplayName("reverse: 正常字符串应反转")
    void testReverse_withNormalString() {
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    @DisplayName("reverse: 空字符串应返回空字符串")
    void testReverse_withEmptyString() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    @DisplayName("reverse: null 输入应返回 null")
    void testReverse_withNull() {
        assertNull(StringUtils.reverse(null));
    }

    @Test
    @DisplayName("reverse: 回文字符串反转后应相同")
    void testReverse_withPalindrome() {
        assertEquals("madam", StringUtils.reverse("madam"));
    }
}
