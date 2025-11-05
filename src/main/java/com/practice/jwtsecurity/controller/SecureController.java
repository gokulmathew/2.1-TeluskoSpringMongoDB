package com.practice.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure-data")
    public String getSecureData() {
        return "✅ Access granted — this is protected data visible only to authenticated users!";
    }
}
