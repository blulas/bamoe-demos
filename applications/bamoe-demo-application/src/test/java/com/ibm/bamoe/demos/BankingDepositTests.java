package com.ibm.bamoe.demos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.model.DepositType;
import com.ibm.bamoe.demos.model.BankingCustomer;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

public class BankingDepositTests {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositTests.class);
    
    @Test
    void testBankDepositRules() {

        logger.info("Testing bank deposit rules....");

        // Prepare some test data
        double maxAvailabilityAmount = new Double(500);
        List<Deposit> deposits = new ArrayList<Deposit>();

        // Add some deposits
        deposits.add(new Deposit(DepositType.PersonalCheck,  400,     maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PersonalCheck,  1200,    maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.Cash,           850,     maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.WireTransfer,   10000,   maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PayrollCheck,   7250,    maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Skyler", 48)));
        deposits.add(new Deposit(DepositType.OfficialCheck,  1750,    maxAvailabilityAmount, new BankingCustomer("123498464", "White", "Skyler", 48)));

        // Invoke the rule service
        try {

            BankingDepositRules rules = new BankingDepositRules();
            RuleResults results = rules.processDeposits(maxAvailabilityAmount, deposits);
            logger.info("Banking Deposit Rules: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 
