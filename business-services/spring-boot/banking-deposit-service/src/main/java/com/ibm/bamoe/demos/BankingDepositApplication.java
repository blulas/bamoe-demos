package com.ibm.bamoe.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ibm.bamoe.demos.**", "rules.**" })
public class BankingDepositApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingDepositApplication.class, args);
    }
}
