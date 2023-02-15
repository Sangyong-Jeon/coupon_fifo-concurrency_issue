package com.hexagonal.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouponFifoConcurrencyIssueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponFifoConcurrencyIssueApplication.class, args);
    }

}
