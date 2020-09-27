package com.cache.ttl.service;

import com.cache.ttl.domain.OtpContainer;
import com.cache.ttl.util.DateTimeUtils;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpGenerator {

    private static final Map<String, OtpContainer> OTP_S = ExpiringMap.builder()
            .maxSize(50)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .expiration(5, TimeUnit.MINUTES)
            .build();

    public OtpContainer get(final String email) {
        if (Objects.nonNull(OTP_S.get(email)))
            return OTP_S.get(email);

        String otp = generateOTP();
        OtpContainer container = new OtpContainer(otp, DateTimeUtils.nowEpoch());
        OTP_S.put(email, container);
        return container;
    }

    private String generateOTP(){
        SplittableRandom splittableRandom = new SplittableRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(splittableRandom.nextInt(0,10));
        }

        return builder.toString();
    }
}
