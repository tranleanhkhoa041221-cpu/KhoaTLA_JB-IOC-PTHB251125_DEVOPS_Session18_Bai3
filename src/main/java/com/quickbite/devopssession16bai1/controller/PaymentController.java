package com.quickbite.devopssession16bai1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/process")
    public String processPayment() {
        logger.info("INFO: Processing payment transaction...");
        logger.debug("DEBUG: Payment transaction data payload details...");
        return "Payment Processed Successfully";
    }
}
