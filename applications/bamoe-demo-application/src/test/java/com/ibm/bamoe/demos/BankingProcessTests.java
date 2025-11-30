package com.ibm.bamoe.demos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.bamoe.demos.model.Customer;
import com.ibm.bamoe.demos.model.Recommendation;
import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.embedded.BankingProcess;

public class BankingProcessTests {

    private static final Logger logger = LoggerFactory.getLogger(BankingProcessTests.class);
    
    //@Test
    void testBankingProcess() {

        logger.info("Testing banking process....");

        // Prepare some test data
        List<Customer> customerList = new ArrayList<Customer>();

        Customer cust1 = new Customer();
        cust1.setId("727225345354");
        cust1.setLastName("White");
        cust1.setFirstName("Walter");
        cust1.setAge(72);
        customerList.add(cust1);

        Customer cust2 = new Customer();
        cust2.setId("13431451345");
        cust2.setLastName("White");
        cust2.setFirstName("Skyler");
        cust2.setAge(60);
        customerList.add(cust2);

        Customer cust3 = new Customer();
        cust3.setId("6453454345");
        cust3.setLastName("White");
        cust3.setFirstName("Walter Junior");
        cust3.setAge(16);

        Recommendation cust3Recommendation = new Recommendation();
        cust3Recommendation.setActionTaken("Verify Age");
        cust3.setRecommendation(cust3Recommendation);
        customerList.add(cust3);

        Customer cust4 = new Customer();
        cust4.setId("452754");
        cust4.setLastName("Goodman");
        cust4.setFirstName("Saul");
        cust4.setAge(62);

        Recommendation cust4Recommendation = new Recommendation();
        cust4Recommendation.setActionTaken("Default");
        cust4.setRecommendation(cust4Recommendation);
        customerList.add(cust4);

        Violation violation = new Violation();
        violation.setType("speed");
        violation.setSpeedLimit(100);
        violation.setActualSpeed(120);

        Driver driver = new Driver();
        driver.setName("Walter White");
        driver.setAge(55);
        driver.setCity("Albuquerque");
        driver.setState("New Mexico");
        driver.setPoints(2);

        // Invoke the rule service
        try {

            BankingProcess process = new BankingProcess();
            RuleResults results = process.startProcess(customerList, violation, driver);
            logger.info("Banking Process: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 
