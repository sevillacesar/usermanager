package com.sevilla.usermanager.util;

import java.util.UUID;

public final class UuidUtil {
    private static final Integer UUID_MAX_LENGTH = 20;
    public static String UuidGenerate() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, UUID_MAX_LENGTH.intValue());
    }
}
