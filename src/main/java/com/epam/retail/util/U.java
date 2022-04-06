package com.epam.retail.util;

import java.util.UUID;

public class U {
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }

    public static boolean hasEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) return true;
        }
        return false;
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
