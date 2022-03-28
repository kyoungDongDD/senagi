package com.ssafy.b105.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class TimeConverter {
    //localDateTime => UnixTime
    public Long localDateTimeToUnix(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }

    //UnixTime => localDateTime
    public LocalDateTime unixToLocalDateTime(Long unixTime) {
        LocalDateTime systemLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), TimeZone.getDefault().toZoneId());
        return systemLocalDateTime;

    }
}
