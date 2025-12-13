package com.ibm.bamoe.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ibm.bamoe.demos.**", "org.acme.**", "org.kie.**", "org.drools.**", "org.jbpm.**" })
public class HiringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiringApplication.class, args);
    }
}
