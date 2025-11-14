package com.citi.bamoe.demos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;
import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;

import com.ibm.techsales.bamoe.demos.model.Deposit;
import com.ibm.techsales.bamoe.demos.model.BankingCustomer;
import com.ibm.techsales.bamoe.demos.model.Customer;
import com.ibm.techsales.bamoe.demos.model.Recommendation;
import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

import com.ibm.techsales.bamoe.demos.embedded.CustomerRules;
import com.ibm.techsales.bamoe.demos.embedded.BankingProcess;
import com.ibm.techsales.bamoe.demos.embedded.BankingDepositRules;
import com.ibm.techsales.bamoe.demos.embedded.TrafficViolationDecisionModel;

public class ApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Test
    void testBankingProcess() {

        logger.info("Testing Banking process....");

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

            BankingProcess ruleSet = new BankingProcess();
            RuleResults results = ruleSet.startProcess(customerList, violation, driver);
            logger.info("Banking Process: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    @Test
    void testCustomerRules() {

        logger.info("Testing Customer rules....");

        // Prepare some test data
        List<Customer> customers = new ArrayList<Customer>();

        Customer cust1 = new Customer();
        cust1.setId("727225345354");
        cust1.setLastName("White");
        cust1.setFirstName("Walter");
        cust1.setAge(72);
        customers.add(cust1);

        Customer cust2 = new Customer();
        cust2.setId("13431451345");
        cust2.setLastName("White");
        cust2.setFirstName("Skyler");
        cust2.setAge(60);
        customers.add(cust2);

        Customer cust3 = new Customer();
        cust3.setId("6453454345");
        cust3.setLastName("White");
        cust3.setFirstName("Walter Junior");
        cust3.setAge(16);

        Recommendation cust3Recommendation = new Recommendation();
        cust3Recommendation.setActionTaken("Verify Age");
        cust3.setRecommendation(cust3Recommendation);
        customers.add(cust3);

        Customer cust4 = new Customer();
        cust4.setId("452754");
        cust4.setLastName("Goodman");
        cust4.setFirstName("Saul");
        cust4.setAge(62);

        Recommendation cust4Recommendation = new Recommendation();
        cust4Recommendation.setActionTaken("Default");
        cust4.setRecommendation(cust4Recommendation);
        customers.add(cust4);

        // Invoke the rule service
        try {

            CustomerRules ruleSet = new CustomerRules();
            RuleResults results = ruleSet.processCustomers(customers);
            logger.info("Customer Rules: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    @Test
    void testBankDepositRules() {

        logger.info("Testing Bank Deposit rules....");

        // Prepare some test data
        Integer maxAmount = new Integer(5000);
        List<Deposit> deposits = new ArrayList<Deposit>();

        Deposit d1 = new Deposit();
        d1.setId("ABC10001");
        d1.setAmount(3000);
        d1.setDeposit(100);

        BankingCustomer c1 = new BankingCustomer();
        c1.setLastName("White");
        c1.setFirstName("Walter");
        c1.setAge(45);
        d1.setCustomer(c1);
        deposits.add(d1);

        Deposit d2 = new Deposit();
        d2.setId("ABC10002");
        d2.setAmount(5000);
        d2.setDeposit(100);

        BankingCustomer c2 = new BankingCustomer();
        c2.setLastName("White");
        c2.setFirstName("Holly");
        c2.setAge(3);
        d2.setCustomer(c2);
        deposits.add(d2);

        Deposit d3 = new Deposit();
        d3.setId("ABC10015");
        d3.setAmount(1000);
        d3.setDeposit(100);

        BankingCustomer c3 = new BankingCustomer();
        c3.setLastName("White");
        c3.setFirstName("Walter Jr");
        c3.setAge(12);
        d3.setCustomer(c3);
        deposits.add(d3);

        Deposit d4 = new Deposit();
        d4.setId("ABC10015");
        d4.setAmount(1000);
        d4.setDeposit(100);

        BankingCustomer c4 = new BankingCustomer();
        c4.setLastName("Goodman");
        c4.setFirstName("Saul");
        c4.setAge(62);
        d4.setCustomer(c4);
        deposits.add(d4);

        // Invoke the rule service
        try {

            BankingDepositRules ruleSet = new BankingDepositRules();
            RuleResults results = ruleSet.processDeposits(maxAmount, deposits);
            logger.info("Bank Deposit Rules: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    @Test
    void testTrafficViolationDecisionModel() {

        logger.info("Testing Traffic Violation decision model....");

        // Prepare some test data
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

        // Invoke the decision model service
        try {

            TrafficViolationDecisionModel decisionModel = new TrafficViolationDecisionModel();
            DecisionModelResults results = decisionModel.processTrafficViolation(violation, driver);
            logger.info("Traffic Violation Decision Model: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 