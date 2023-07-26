package com.example.limitoffer;

import com.example.account.CreditCardLimitApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.limitoffer","com.example.account"})
public class LimitOfferMain {
    public static void main(String[] args) {

        SpringApplication.run(LimitOfferMain.class, args);
    }
}
