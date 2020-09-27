package com.cache.ttl.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.StringJoiner;

public final class OtpContainer {
    private static final long DURABILITY_SECONDS = 300;
    private static final String ZONE_OFFSET = "+09:00";

    private final String otp;
    private final long ttl;
    private final long createdAt;

    public OtpContainer(String otp, long createdAt) {
        this.otp = otp;
        this.ttl = createdAt + DURABILITY_SECONDS;
        this.createdAt = createdAt;
    }

    public String getOtp() {
        return otp;
    }

    public long getTtlSeconds() {
        Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        return ttl - instant.getEpochSecond();
    }

    public LocalDateTime getWillDieAt() {
        return LocalDateTime.ofEpochSecond(this.ttl, 0, ZoneOffset.of(ZONE_OFFSET));
    }

    public LocalDateTime getCreatedAt() {
        return LocalDateTime.ofEpochSecond(this.createdAt, 0, ZoneOffset.of(ZONE_OFFSET));
    }

    @Override
    public String toString() {
        LocalDateTime createdDateTime = LocalDateTime.ofEpochSecond(this.createdAt, 0, ZoneOffset.of(ZONE_OFFSET));
        LocalDateTime ttlDateTime = LocalDateTime.ofEpochSecond(this.ttl, 0, ZoneOffset.of(ZONE_OFFSET));
        return new StringJoiner(", ", OtpContainer.class.getSimpleName() + "[", "]")
                .add("otp='" + otp + "'")
                .add("ttl=" + ttlDateTime)
                .add("createdAt=" + createdDateTime)
                .toString();
    }
}
