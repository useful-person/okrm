package com.example.demo.utils;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTests {
	@Test
    @DisplayName("toUpperCase 应该正确转换大写")
    void testToUpperCase() {
        assertEquals("HELLO", StringUtils.toUpperCase("hello"));
        assertEquals("WORLD", StringUtils.toUpperCase("World"));
        assertNull(StringUtils.toUpperCase(null));
    }

    @Test
    @DisplayName("reverse 应该正确反转字符串")
    void testReverse() {
        assertEquals("olleH", StringUtils.reverse("Hello"));
        assertEquals("dlroW", StringUtils.reverse("World"));
        assertNull(StringUtils.reverse(null));
    }
}
