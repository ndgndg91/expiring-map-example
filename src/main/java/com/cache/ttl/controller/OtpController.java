package com.cache.ttl.controller;

import com.cache.ttl.domain.OtpContainer;
import com.cache.ttl.service.OtpGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    private static final Logger log = LoggerFactory.getLogger(OtpController.class);

    private final OtpGenerator otpGenerator;

    public OtpController(OtpGenerator otpGenerator) {
        this.otpGenerator = otpGenerator;
    }

    @GetMapping("/otp/test")
    public ResponseEntity<OtpContainer> otp(){
        OtpContainer body = otpGenerator.get("donggil@sixshop.com");
        log.info("{}", body);
        return ResponseEntity.ok(body);
    }
}
