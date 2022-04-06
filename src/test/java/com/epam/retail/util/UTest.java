package com.epam.retail.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit Test
class UTest {

    @Test
    void isEmpty() {
        assertTrue(U.isEmpty(""));
        assertFalse(U.isEmpty("null"));
    }

    @Test
    void hasEmpty() {
        assertTrue(U.hasEmpty("first", "second", ""));
        assertTrue(U.hasEmpty("first", "second", null));
        assertFalse(U.hasEmpty("first", "second", "null"));
    }
}
