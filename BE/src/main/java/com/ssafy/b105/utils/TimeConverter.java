package com.ssafy.b105.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class TimeConverter {
    //localDateTime => UnixTime
<<<<<<< HEAD
    public Long localDateTimeToUnix(LocalDateTime localDateTime) {
=======
    public static Long localDateTimeToUnix(LocalDateTime localDateTime) {
>>>>>>> dev
        return Timestamp.valueOf(localDateTime).getTime();
    }

    //UnixTime => localDateTime
<<<<<<< HEAD
    public LocalDateTime unixToLocalDateTime(Long unixTime) {
=======
    public static LocalDateTime unixToLocalDateTime(Long unixTime) {
>>>>>>> dev
        LocalDateTime systemLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), TimeZone.getDefault().toZoneId());
        return systemLocalDateTime;

    }
}
