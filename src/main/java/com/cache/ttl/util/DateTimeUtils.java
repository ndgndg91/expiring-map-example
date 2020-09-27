package com.cache.ttl.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateTimeUtils {

    private DateTimeUtils(){}

    public static long nowEpoch(){
        return LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .getEpochSecond();
    }

}
