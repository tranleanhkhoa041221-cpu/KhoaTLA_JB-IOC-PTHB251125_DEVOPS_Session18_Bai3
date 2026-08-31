package com.quickbite.devopssession16bai1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/error")
    public String triggerError() {
        logger.error("Loi DB: Connection pool exhausted!");
        return "Triggered ERROR log successfully!";
    }
}
